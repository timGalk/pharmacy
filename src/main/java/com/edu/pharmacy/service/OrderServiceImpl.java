package com.edu.pharmacy.service;

import com.edu.pharmacy.DTO.cart.CartDTO;
import com.edu.pharmacy.DTO.order.OrderCreateDTO;
import com.edu.pharmacy.DTO.order.OrderDTO;
import com.edu.pharmacy.DTO.order.OrderUpdateDTO;
import com.edu.pharmacy.entity.*;
import com.edu.pharmacy.mapper.OrderMapper;
import com.edu.pharmacy.repository.MedicineRepository;
import com.edu.pharmacy.repository.OrderItemRepository;
import com.edu.pharmacy.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final CartService cartService;
    private final MedicineRepository medicineRepository;
    private final OrderSecurityService orderSecurityService;

    @Override
    @Transactional
    public OrderDTO createOrderFromCart(OrderCreateDTO orderCreateDTO) {
        // Get the cart
        CartDTO cart = cartService.getCart(orderCreateDTO.getCartId());
        
        if (cart.getItemList().isEmpty()) {
            throw new RuntimeException("Cannot create order from empty cart");
        }

        // Calculate total amount
        BigDecimal totalAmount = cart.getItemList().stream()
                .map(item -> item.getMedicine().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Create order entity
        OrderEntity order = OrderEntity.builder()
                .userId(cart.getUserId())
                .status(OrderStatus.PENDING)
                .totalAmount(totalAmount)
                .shippingAddress(orderCreateDTO.getShippingAddress())
                .phoneNumber(orderCreateDTO.getPhoneNumber())
                .customerName(orderCreateDTO.getCustomerName())
                .items(new ArrayList<>())
                .build();

        OrderEntity savedOrder = orderRepository.save(order);

        // Create order items and update stock
        List<OrderItemEntity> orderItems = new ArrayList<>();
        for (var cartItem : cart.getItemList()) {
            MedicineEntity medicine = medicineRepository.findById(cartItem.getMedicine().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Medicine not found"));

            // Check stock availability
            if (medicine.getStockQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException("Insufficient stock for medicine: " + medicine.getName());
            }

            // Update stock
            medicine.setStockQuantity(medicine.getStockQuantity() - cartItem.getQuantity());
            medicineRepository.save(medicine);

            // Create order item
            OrderItemEntity orderItem = OrderItemEntity.builder()
                    .order(savedOrder)
                    .medicine(medicine)
                    .quantity(cartItem.getQuantity())
                    .unitPrice(medicine.getPrice())
                    .totalPrice(medicine.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                    .build();

            OrderItemEntity savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }

        savedOrder.setItems(orderItems);
        orderRepository.save(savedOrder);

        // Clear the cart after successful order creation
        cartService.clearCart(orderCreateDTO.getCartId());

        log.info("Created order {} from cart {} for user {}", savedOrder.getId(), orderCreateDTO.getCartId(), cart.getUserId());
        return orderMapper.convert(savedOrder);
    }

    @Override
    @Transactional
    public OrderDTO getOrder(Long orderId) {
        OrderEntity order = orderSecurityService.checkOrderAccess(orderId);
        return orderMapper.convert(order);
    }

    @Override
    @Transactional
    public List<OrderDTO> getOrdersByUserId(Long userId) {
        orderSecurityService.checkUserOrdersAccess(userId);
        List<OrderEntity> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(orderMapper::convert)
                .toList();
    }

    @Override
    @Transactional
    public List<OrderDTO> getOrdersByStatus(OrderStatus status) {
        List<OrderEntity> orders = orderRepository.findByStatus(status);
        return orders.stream()
                .map(orderMapper::convert)
                .toList();
    }

    @Override
    @Transactional
    public List<OrderDTO> getAllOrders() {
        List<OrderEntity> orders = orderRepository.findAll();
        return orders.stream()
                .map(orderMapper::convert)
                .toList();
    }

    @Override
    @Transactional
    public OrderDTO updateOrderStatus(Long orderId, OrderUpdateDTO orderUpdateDTO) {
        OrderEntity order = orderSecurityService.checkOrderAccess(orderId);

        // Business logic for status transitions
        if (order.getStatus() == OrderStatus.CANCELLED || order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException("Cannot update status of " + order.getStatus() + " order");
        }

        // If cancelling order, restore stock
        if (orderUpdateDTO.getStatus() == OrderStatus.CANCELLED && order.getStatus() != OrderStatus.CANCELLED) {
            restoreStock(order);
        }

        order.setStatus(orderUpdateDTO.getStatus());
        OrderEntity savedOrder = orderRepository.save(order);
        
        log.info("Updated order {} status to {}", orderId, orderUpdateDTO.getStatus());
        return orderMapper.convert(savedOrder);
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(Long orderId) {
        OrderUpdateDTO cancelDTO = OrderUpdateDTO.builder()
                .status(OrderStatus.CANCELLED)
                .build();
        return updateOrderStatus(orderId, cancelDTO);
    }

    private void restoreStock(OrderEntity order) {
        for (OrderItemEntity orderItem : order.getItems()) {
            MedicineEntity medicine = orderItem.getMedicine();
            medicine.setStockQuantity(medicine.getStockQuantity() + orderItem.getQuantity());
            medicineRepository.save(medicine);
        }
        log.info("Restored stock for cancelled order {}", order.getId());
    }
} 
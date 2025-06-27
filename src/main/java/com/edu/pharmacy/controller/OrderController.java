package com.edu.pharmacy.controller;

import com.edu.pharmacy.DTO.order.OrderCreateDTO;
import com.edu.pharmacy.DTO.order.OrderDTO;
import com.edu.pharmacy.DTO.order.OrderUpdateDTO;
import com.edu.pharmacy.entity.OrderStatus;
import com.edu.pharmacy.security.user.AuthUser;
import com.edu.pharmacy.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;

    /**
     * Create order from cart
     */
    @PostMapping
    public ResponseEntity<OrderDTO> createOrderFromCart(@Validated @RequestBody OrderCreateDTO orderCreateDTO) {
        OrderDTO order = orderService.createOrderFromCart(orderCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    /**
     * Get order by ID
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable Long orderId) {
        OrderDTO order = orderService.getOrder(orderId);
        return ResponseEntity.ok(order);
    }

    /**
     * Get orders for the currently authenticated user
     */
    @GetMapping("/user")
    public ResponseEntity<List<OrderDTO>> getCurrentUserOrders(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        List<OrderDTO> orders = orderService.getOrdersByUserId(authUser.userId());
        return ResponseEntity.ok(orders);
    }

    /**
     * Get all orders for a specific user (admin/pharmacist only)
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUserId(@PathVariable Long userId) {
        List<OrderDTO> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }

    /**
     * Get all orders with specific status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable OrderStatus status) {
        List<OrderDTO> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    /**
     * Get all orders (admin only)
     */
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    /**
     * Update order status
     */
    @PatchMapping("/{orderId}/status")
    public ResponseEntity<OrderDTO> updateOrderStatus(
            @PathVariable Long orderId,
            @Validated @RequestBody OrderUpdateDTO orderUpdateDTO) {
        OrderDTO order = orderService.updateOrderStatus(orderId, orderUpdateDTO);
        return ResponseEntity.ok(order);
    }

    /**
     * Cancel order
     */
    @PostMapping("/{orderId}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Long orderId) {
        OrderDTO order = orderService.cancelOrder(orderId);
        return ResponseEntity.ok(order);
    }
} 
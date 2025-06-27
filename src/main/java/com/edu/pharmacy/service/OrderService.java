package com.edu.pharmacy.service;

import com.edu.pharmacy.DTO.order.OrderCreateDTO;
import com.edu.pharmacy.DTO.order.OrderDTO;
import com.edu.pharmacy.DTO.order.OrderUpdateDTO;
import com.edu.pharmacy.entity.OrderStatus;

import java.util.List;

public interface OrderService {
    /**
     * Creates a new order from a user's cart.
     *
     * @param orderCreateDTO The order creation data including cart ID and shipping info.
     * @return OrderDTO representing the created order.
     */
    OrderDTO createOrderFromCart(OrderCreateDTO orderCreateDTO);

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId The ID of the order.
     * @return OrderDTO representing the order.
     */
    OrderDTO getOrder(Long orderId);

    /**
     * Retrieves all orders for a specific user.
     *
     * @param userId The ID of the user.
     * @return List of OrderDTO representing the user's orders.
     */
    List<OrderDTO> getOrdersByUserId(Long userId);

    /**
     * Retrieves all orders with a specific status.
     *
     * @param status The status to filter by.
     * @return List of OrderDTO representing orders with the specified status.
     */
    List<OrderDTO> getOrdersByStatus(OrderStatus status);

    /**
     * Retrieves all orders in the system.
     *
     * @return List of OrderDTO representing all orders.
     */
    List<OrderDTO> getAllOrders();

    /**
     * Updates the status of an order.
     *
     * @param orderId The ID of the order to update.
     * @param orderUpdateDTO The updated order data.
     * @return OrderDTO representing the updated order.
     */
    OrderDTO updateOrderStatus(Long orderId, OrderUpdateDTO orderUpdateDTO);

    /**
     * Cancels an order.
     *
     * @param orderId The ID of the order to cancel.
     * @return OrderDTO representing the cancelled order.
     */
    OrderDTO cancelOrder(Long orderId);
} 
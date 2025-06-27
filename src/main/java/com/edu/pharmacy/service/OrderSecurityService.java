package com.edu.pharmacy.service;

import com.edu.pharmacy.entity.OrderEntity;
import com.edu.pharmacy.repository.OrderRepository;
import com.edu.pharmacy.security.user.AuthUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderSecurityService {
    
    private final OrderRepository orderRepository;
    
    /**
     * Checks if the current user has access to the specified order.
     * Users can only access their own orders, while ADMIN and PHARMACIST can access all orders.
     *
     * @param orderId The ID of the order to check access for
     * @return The order entity if access is granted
     * @throws EntityNotFoundException if order doesn't exist
     * @throws AccessDeniedException if user doesn't have access
     */
    public OrderEntity checkOrderAccess(Long orderId) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order with id " + orderId + " not found"));
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        
        // Admin and Pharmacist can access all orders
        if (authUser.roles().stream().anyMatch(role -> 
                role.name().equals("ADMIN") || role.name().equals("PHARMACIST"))) {
            return order;
        }
        
        // Users can only access their own orders
        if (order.getUserId().equals(authUser.userId())) {
            return order;
        }
        
        throw new org.springframework.security.access.AccessDeniedException(
                "Access denied: You can only access your own orders");
    }
    
    /**
     * Checks if the current user has access to orders for a specific user.
     * Users can only access their own orders, while ADMIN and PHARMACIST can access all orders.
     *
     * @param userId The ID of the user whose orders to check access for
     * @return true if access is granted
     * @throws AccessDeniedException if user doesn't have access
     */
    public boolean checkUserOrdersAccess(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        
        // Admin and Pharmacist can access all user orders
        if (authUser.roles().stream().anyMatch(role -> 
                role.name().equals("ADMIN") || role.name().equals("PHARMACIST"))) {
            return true;
        }
        
        // Users can only access their own orders
        if (userId.equals(authUser.userId())) {
            return true;
        }
        
        throw new org.springframework.security.access.AccessDeniedException(
                "Access denied: You can only access your own orders");
    }
} 
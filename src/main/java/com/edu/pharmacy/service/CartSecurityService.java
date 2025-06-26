package com.edu.pharmacy.service;

import com.edu.pharmacy.entity.CartEntity;
import com.edu.pharmacy.repository.CartRepository;
import com.edu.pharmacy.security.user.AuthUser;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartSecurityService {
    
    private final CartRepository cartRepository;
    
    /**
     * Checks if the current user has access to the specified cart.
     * Only users can access carts, and only their own carts.
     * Admin and Pharmacist cannot access carts.
     *
     * @param cartId The ID of the cart to check access for
     * @return The cart entity if access is granted
     * @throws EntityNotFoundException if cart doesn't exist
     * @throws AccessDeniedException if user doesn't have access or is admin/pharmacist
     */
    public CartEntity checkCartAccess(Long cartId) {
        CartEntity cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart with id " + cartId + " not found"));
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        
        // Only users can access carts
        if (authUser.roles().stream().anyMatch(role -> 
                role.name().equals("ADMIN") || role.name().equals("PHARMACIST"))) {
            throw new org.springframework.security.access.AccessDeniedException(
                    "Access denied: Admin and Pharmacist cannot access carts");
        }
        
        // Users can only access their own carts
        if (cart.getUserId().equals(authUser.userId())) {
            return cart;
        }
        
        throw new org.springframework.security.access.AccessDeniedException(
                "Access denied: You can only access your own cart");
    }
    
    /**
     * Checks if the current user has access to cart for a specific user.
     * Only users can access carts, and only their own carts.
     * Admin and Pharmacist cannot access carts.
     *
     * @param userId The ID of the user whose cart to check access for
     * @return true if access is granted
     * @throws AccessDeniedException if user doesn't have access or is admin/pharmacist
     */
    public boolean checkUserCartAccess(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        
        // Only users can access carts
        if (authUser.roles().stream().anyMatch(role -> 
                role.name().equals("ADMIN") || role.name().equals("PHARMACIST"))) {
            throw new org.springframework.security.access.AccessDeniedException(
                    "Access denied: Admin and Pharmacist cannot access carts");
        }
        
        // Users can only access their own carts
        if (userId.equals(authUser.userId())) {
            return true;
        }
        
        throw new org.springframework.security.access.AccessDeniedException(
                "Access denied: You can only access your own cart");
    }
} 
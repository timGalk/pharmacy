package com.edu.pharmacy.controller;

import com.edu.pharmacy.DTO.cart.CartDTO;
import com.edu.pharmacy.DTO.cart.CartItemCreateDTO;
import com.edu.pharmacy.DTO.cart.CartItemUpdateDTO;
import com.edu.pharmacy.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {
    
    private final CartService cartService;

    /**
     * Get cart by cart ID
     */
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long cartId) {
        CartDTO cart = cartService.getCart(cartId);
        return ResponseEntity.ok(cart);
    }

    /**
     * Get cart by user ID
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<CartDTO> getCartByUserId(@PathVariable Long userId) {
        CartDTO cart = cartService.getCartByUserId(userId);
        return ResponseEntity.ok(cart);
    }

    /**
     * Create a new cart for a user
     */
    @PostMapping("/user/{userId}")
    public ResponseEntity<CartDTO> createCart(@PathVariable Long userId) {
        CartDTO cart = cartService.createCart(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    /**
     * Add item to cart
     */
    @PostMapping("/items")
    public ResponseEntity<CartDTO> addItemToCart(@Validated @RequestBody CartItemCreateDTO cartItemCreateDTO) {
        CartDTO cart = cartService.addItemToCart(cartItemCreateDTO);
        return ResponseEntity.ok(cart);
    }

    /**
     * Update cart item quantity
     */
    @PatchMapping("/items/{itemId}")
    public ResponseEntity<CartDTO> updateCartItem(
            @PathVariable Long itemId,
            @Validated @RequestBody CartItemUpdateDTO cartItemUpdateDTO) {
        CartDTO cart = cartService.updateCartItem(itemId, cartItemUpdateDTO);
        return ResponseEntity.ok(cart);
    }

    /**
     * Remove item from cart
     */
    @DeleteMapping("/items/{itemId}")
    public ResponseEntity<CartDTO> removeItemFromCart(@PathVariable Long itemId) {
        CartDTO cart = cartService.removeItemFromCart(itemId);
        return ResponseEntity.ok(cart);
    }

    /**
     * Clear all items from cart
     */
    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<CartDTO> clearCart(@PathVariable Long cartId) {
        CartDTO cart = cartService.clearCart(cartId);
        return ResponseEntity.ok(cart);
    }
} 
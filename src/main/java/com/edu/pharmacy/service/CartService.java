package com.edu.pharmacy.service;

import com.edu.pharmacy.DTO.cart.CartDTO;
import com.edu.pharmacy.DTO.cart.CartItemDTO;

public interface CartService {
    /**
     * Retrieves the cart for the current user.
     *
     * @return CartDTO representing the user's cart.
     */
    CartDTO getCart(Long cartId);

    /**
     * Adds an item to the user's cart.
     *
     * @param cartItemDTO The item to be added to the cart.
     * @return Updated CartDTO after adding the item.
     */
    CartDTO addItemToCart(CartItemDTO cartItemDTO);

    /**
     * Removes an item from the user's cart.
     *
     * @param itemId The ID of the item to be removed.
     * @return Updated CartDTO after removing the item.
     */
    CartDTO removeItemFromCart(Long itemId);
}

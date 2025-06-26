package com.edu.pharmacy.service;

import com.edu.pharmacy.DTO.cart.CartDTO;
import com.edu.pharmacy.DTO.cart.CartItemCreateDTO;
import com.edu.pharmacy.DTO.cart.CartItemDTO;
import com.edu.pharmacy.DTO.cart.CartItemUpdateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    /**
     * Retrieves the cart for the current user.
     *
     * @return CartDTO representing the user's cart.
     */
    CartDTO getCart(Long cartId);

    /**
     * Retrieves the cart for a specific user.
     *
     * @param userId The ID of the user.
     * @return CartDTO representing the user's cart.
     */
    CartDTO getCartByUserId(Long userId);

    /**
     * Creates a new cart for a user.
     *
     * @param userId The ID of the user.
     * @return CartDTO representing the created cart.
     */
    CartDTO createCart(Long userId);

    /**
     * Adds an item to the user's cart.
     *
     * @param cartItemCreateDTO The item to be added to the cart.
     * @return Updated CartDTO after adding the item.
     */
    CartDTO addItemToCart(CartItemCreateDTO cartItemCreateDTO);

    /**
     * Updates the quantity of an item in the cart.
     *
     * @param itemId The ID of the item to be updated.
     * @param cartItemUpdateDTO The updated quantity.
     * @return Updated CartDTO after updating the item.
     */
    CartDTO updateCartItem(Long itemId, CartItemUpdateDTO cartItemUpdateDTO);

    /**
     * Removes an item from the user's cart.
     *
     * @param itemId The ID of the item to be removed.
     * @return Updated CartDTO after removing the item.
     */
    CartDTO removeItemFromCart(Long itemId);

    /**
     * Clears all items from a user's cart.
     *
     * @param cartId The ID of the cart to clear.
     * @return Updated CartDTO with empty cart.
     */
    CartDTO clearCart(Long cartId);
}

package com.edu.pharmacy.service;

import com.edu.pharmacy.DTO.cart.CartDTO;
import com.edu.pharmacy.DTO.cart.CartItemCreateDTO;
import com.edu.pharmacy.DTO.cart.CartItemDTO;
import com.edu.pharmacy.DTO.cart.CartItemUpdateDTO;
import com.edu.pharmacy.entity.CartEntity;
import com.edu.pharmacy.entity.CartItemEntity;
import com.edu.pharmacy.entity.MedicineEntity;
import com.edu.pharmacy.mapper.CartMapper;
import com.edu.pharmacy.repository.CartItemRepository;
import com.edu.pharmacy.repository.CartRepository;
import com.edu.pharmacy.repository.MedicineRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final MedicineRepository medicineRepository;
    private final CartMapper cartMapper;

    @Override
    @Transactional
    public CartDTO getCart(Long id) {
        CartEntity cartEntity = cartRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cart with id " + id + " not found"));
        return cartMapper.convert(cartEntity);
    }

    @Override
    @Transactional
    public CartDTO getCartByUserId(Long userId) {
        CartEntity cartEntity = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Cart for user " + userId + " not found"));
        return cartMapper.convert(cartEntity);
    }

    @Override
    @Transactional
    public CartDTO createCart(Long userId) {
        // Check if cart already exists for this user
        Optional<CartEntity> existingCart = cartRepository.findByUserId(userId);
        if (existingCart.isPresent()) {
            throw new RuntimeException("Cart already exists for user " + userId);
        }

        CartEntity cartEntity = CartEntity.builder()
                .userId(userId)
                .items(new ArrayList<>())
                .build();
        
        CartEntity savedCart = cartRepository.save(cartEntity);
        log.info("Created new cart with id {} for user {}", savedCart.getId(), userId);
        return cartMapper.convert(savedCart);
    }

    @Override
    @Transactional
    public CartDTO addItemToCart(CartItemCreateDTO cartItemCreateDTO) {
        // Find the cart
        CartEntity cart = cartRepository.findById(cartItemCreateDTO.getCartId())
                .orElseThrow(() -> new EntityNotFoundException("Cart with id " + cartItemCreateDTO.getCartId() + " not found"));

        // Find the medicine
        MedicineEntity medicine = medicineRepository.findById(cartItemCreateDTO.getMedicineId())
                .orElseThrow(() -> new EntityNotFoundException("Medicine with id " + cartItemCreateDTO.getMedicineId() + " not found"));

        // Check if item already exists in cart
        Optional<CartItemEntity> existingItem = cart.getItems().stream()
                .filter(item -> item.getMedicine().getId().equals(cartItemCreateDTO.getMedicineId()))
                .findFirst();

        if (existingItem.isPresent()) {
            // Update quantity of existing item
            CartItemEntity item = existingItem.get();
            item.setQuantity(item.getQuantity() + cartItemCreateDTO.getQuantity());
            cartItemRepository.save(item);
            log.info("Updated quantity for existing item in cart {}", cart.getId());
        } else {
            // Create new cart item
            CartItemEntity newItem = CartItemEntity.builder()
                    .cart(cart)
                    .medicine(medicine)
                    .quantity(cartItemCreateDTO.getQuantity())
                    .build();
            
            cartItemRepository.save(newItem);
            cart.getItems().add(newItem);
            log.info("Added new item to cart {}", cart.getId());
        }

        cartRepository.save(cart);
        return cartMapper.convert(cart);
    }

    @Override
    @Transactional
    public CartDTO updateCartItem(Long itemId, CartItemUpdateDTO cartItemUpdateDTO) {
        CartItemEntity cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item with id " + itemId + " not found"));

        cartItem.setQuantity(cartItemUpdateDTO.getQuantity());
        cartItemRepository.save(cartItem);
        
        log.info("Updated quantity for cart item {}", itemId);
        return cartMapper.convert(cartItem.getCart());
    }

    @Override
    @Transactional
    public CartDTO removeItemFromCart(Long itemId) {
        CartItemEntity cartItem = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Cart item with id " + itemId + " not found"));

        CartEntity cart = cartItem.getCart();
        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        cartRepository.save(cart);
        
        log.info("Removed item {} from cart {}", itemId, cart.getId());
        return cartMapper.convert(cart);
    }

    @Override
    @Transactional
    public CartDTO clearCart(Long cartId) {
        CartEntity cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Cart with id " + cartId + " not found"));

        cartItemRepository.deleteAll(cart.getItems());
        cart.getItems().clear();
        cartRepository.save(cart);
        
        log.info("Cleared all items from cart {}", cartId);
        return cartMapper.convert(cart);
    }
}

package com.edu.pharmacy.service;

import com.edu.pharmacy.DTO.cart.CartDTO;
import com.edu.pharmacy.DTO.cart.CartItemDTO;
import com.edu.pharmacy.entity.CartEntity;
import com.edu.pharmacy.mapper.CartMapper;
import com.edu.pharmacy.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    @Override
    @Transactional
    public CartDTO getCart( Long id ) {
        CartEntity cartEntity = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart wasn't found"));
        return cartMapper.convert(cartEntity);
    }

    @Override
    @Transactional
    public CartDTO addItemToCart(CartItemDTO cartItemDTO) {
        return null;
    }

    @Override
    @Transactional
    public CartDTO removeItemFromCart(Long itemId) {
        return null;
    }
}

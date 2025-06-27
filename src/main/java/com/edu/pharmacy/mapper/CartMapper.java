package com.edu.pharmacy.mapper;

import com.edu.pharmacy.DTO.cart.CartDTO;
import com.edu.pharmacy.DTO.cart.CartItemDTO;
import com.edu.pharmacy.entity.CartEntity;
import com.edu.pharmacy.entity.CartItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartMapper {
    
    private final MedicineMapper medicineMapper;
    
    public CartDTO convert(CartEntity cartEntity) {
        return new CartDTO(
                cartEntity.getId(),
                cartEntity.getUserId(),
                cartEntity.getItems()
                        .stream()
                        .map(this::convert)
                        .toList()
        );
    }
    
    public CartItemDTO convert(CartItemEntity cartItemEntity) {
        return new CartItemDTO(
                cartItemEntity.getId(),
                medicineMapper.convert(cartItemEntity.getMedicine()),
                cartItemEntity.getQuantity()
        );
    }
}

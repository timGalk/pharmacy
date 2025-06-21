package com.edu.pharmacy.mapper;

import com.edu.pharmacy.DTO.cart.CartDTO;
import com.edu.pharmacy.DTO.cart.CartItemDTO;
import com.edu.pharmacy.entity.CartEntity;
import com.edu.pharmacy.entity.CartItemEntity;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
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
        MedicineMapper mapper = new MedicineMapper();

        return new CartItemDTO(
                cartItemEntity.getId(),
                mapper.convert(cartItemEntity.getMedicine()),
                cartItemEntity.getQuantity()

        );
    }

}

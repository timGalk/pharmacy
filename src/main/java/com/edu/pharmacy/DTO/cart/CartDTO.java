package com.edu.pharmacy.DTO.cart;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartDTO {
    private Long  id;
    private Long userId;
    private List<CartItemDTO> itemList;
}

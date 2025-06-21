package com.edu.pharmacy.DTO.cart;

import com.edu.pharmacy.DTO.medicine.MedicineDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDTO {
    private Long id;
    private MedicineDTO medicine;
    private int quantity;

}

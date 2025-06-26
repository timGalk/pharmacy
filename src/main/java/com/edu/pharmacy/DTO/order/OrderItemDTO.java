package com.edu.pharmacy.DTO.order;

import com.edu.pharmacy.DTO.medicine.MedicineDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private Long id;
    private MedicineDTO medicine;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
} 
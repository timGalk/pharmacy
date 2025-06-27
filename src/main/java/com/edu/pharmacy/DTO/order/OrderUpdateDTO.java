package com.edu.pharmacy.DTO.order;

import com.edu.pharmacy.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderUpdateDTO {
    
    @NotNull(message = "Order status is required")
    private OrderStatus status;
} 
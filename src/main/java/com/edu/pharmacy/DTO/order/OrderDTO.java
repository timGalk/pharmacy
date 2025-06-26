package com.edu.pharmacy.DTO.order;

import com.edu.pharmacy.entity.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    private Long userId;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private String shippingAddress;
    private String phoneNumber;
    private String customerName;
    private List<OrderItemDTO> items;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
} 
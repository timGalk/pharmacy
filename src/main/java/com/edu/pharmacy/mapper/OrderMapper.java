package com.edu.pharmacy.mapper;

import com.edu.pharmacy.DTO.order.OrderDTO;
import com.edu.pharmacy.DTO.order.OrderItemDTO;
import com.edu.pharmacy.entity.OrderEntity;
import com.edu.pharmacy.entity.OrderItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    
    private final MedicineMapper medicineMapper;
    
    public OrderDTO convert(OrderEntity orderEntity) {
        return OrderDTO.builder()
                .id(orderEntity.getId())
                .userId(orderEntity.getUserId())
                .status(orderEntity.getStatus())
                .totalAmount(orderEntity.getTotalAmount())
                .shippingAddress(orderEntity.getShippingAddress())
                .phoneNumber(orderEntity.getPhoneNumber())
                .customerName(orderEntity.getCustomerName())
                .items(orderEntity.getItems() != null ? 
                        orderEntity.getItems().stream()
                                .map(this::convert)
                                .toList() : new ArrayList<>())
                .createdAt(orderEntity.getCreatedAt())
                .updatedAt(orderEntity.getUpdatedAt())
                .build();
    }
    
    public OrderItemDTO convert(OrderItemEntity orderItemEntity) {
        return OrderItemDTO.builder()
                .id(orderItemEntity.getId())
                .medicine(medicineMapper.convert(orderItemEntity.getMedicine()))
                .quantity(orderItemEntity.getQuantity())
                .unitPrice(orderItemEntity.getUnitPrice())
                .totalPrice(orderItemEntity.getTotalPrice())
                .build();
    }
} 
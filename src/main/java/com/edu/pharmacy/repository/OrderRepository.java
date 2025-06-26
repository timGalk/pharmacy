package com.edu.pharmacy.repository;

import com.edu.pharmacy.entity.OrderEntity;
import com.edu.pharmacy.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUserId(Long userId);
    List<OrderEntity> findByStatus(OrderStatus status);
    List<OrderEntity> findByUserIdAndStatus(Long userId, OrderStatus status);
} 
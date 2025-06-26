package com.edu.pharmacy.entity;

public enum OrderStatus {
    PENDING,        // Order created but not yet processed
    CONFIRMED,      // Order confirmed by admin
    PROCESSING,     // Order is being prepared
    SHIPPED,        // Order has been shipped
    DELIVERED,      // Order has been delivered
    CANCELLED,      // Order has been cancelled
    REFUNDED        // Order has been refunded
} 
package com.edu.pharmacy.repo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "medicine")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private LocalDate expirationDate;

}



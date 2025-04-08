package com.edu.pharmacy.service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MedicineDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private LocalDate expirationDate;

}

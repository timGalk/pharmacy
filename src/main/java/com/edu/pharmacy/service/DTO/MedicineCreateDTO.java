package com.edu.pharmacy.service.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MedicineCreateDTO {
    private String name;
    private String description;
    private double price;
    private int quantity;
    private LocalDate expirationDate;

}

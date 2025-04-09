package com.edu.pharmacy.service.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MedicineCreateDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity;
    private LocalDate expirationDate;

}

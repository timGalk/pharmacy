package com.edu.pharmacy.service.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class MedicineCreateDTO {
    @Size(min = 3, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;
    @Size(min = 3, max = 200, message = "Description must be between 1 and 200 characters")
    private String description;
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private BigDecimal price;
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private int quantity;
    private LocalDate expirationDate;

}

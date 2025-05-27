package com.edu.pharmacy.DTO.medicine;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object (DTO) for creating a new medicine.
 * This class is used to encapsulate the data required to create a new medicine entity.
 * It uses Lombok annotations for boilerplate code generation and Jakarta Validation annotations for input validation.
 */
@Data
public class MedicineCreateDTO {

    /**
     * The name of the medicine.
     * Must be between 3 and 50 characters.
     */
    @Size(min = 3, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    /**
     * A brief description of the medicine.
     * Must be between 3 and 200 characters.
     */
    @Size(min = 3, max = 200, message = "Description must be between 1 and 200 characters")
    private String description;

    /**
     * The price of the medicine.
     * Must be greater than or equal to 0.
     */
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    private BigDecimal price;

    /**
     * The quantity of the medicine available in stock.
     * Must be greater than or equal to 0.
     */
    @Min(value = 0, message = "Quantity must be greater than or equal to 0")
    private int quantity;

    /**
     * The expiration date of the medicine.
     */
    private LocalDate expirationDate;

}
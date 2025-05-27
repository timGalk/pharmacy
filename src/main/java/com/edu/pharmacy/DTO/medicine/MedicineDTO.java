package com.edu.pharmacy.DTO.medicine;

    import lombok.AllArgsConstructor;
    import lombok.Data;

    import java.math.BigDecimal;
    import java.time.LocalDate;

    /**
     * Data Transfer Object (DTO) representing a medicine.
     * This class is used to transfer medicine-related data between different layers of the application.
     * It uses Lombok annotations for boilerplate code generation.
     */
    @Data
    @AllArgsConstructor
    public class MedicineDTO {

        /**
         * The unique identifier for the medicine.
         */
        private Long id;

        /**
         * The name of the medicine.
         */
        private String name;

        /**
         * A brief description of the medicine.
         */
        private String description;

        /**
         * The price of the medicine.
         */
        private BigDecimal price;

        /**
         * The quantity of the medicine available in stock.
         */
        private int stockQuantity;

        /**
         * The expiration date of the medicine.
         */
        private LocalDate expirationDate;

    }
package com.edu.pharmacy.DTO.user;

import com.edu.pharmacy.common.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * Data Transfer Object (DTO) representing a user.
 * This class is used to transfer user-related data between different layers of the application.
 * It uses Lombok annotations for boilerplate code generation and Bean Validation for input validation.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @Size(max = 255, message = "Address must not exceed 255 characters")
    private String address;

    @Min(value = 0, message = "Age must be non-negative")
    @Max(value = 150, message = "Age must be realistic")
    private int age;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Invalid phone number format")
    private String phoneNumber;


    @NotNull(message = "Roles must not be null")
    @Size(min = 1, message = "User must have at least one role")
    private Set<@NotNull(message = "Role must not be null") Role> roles;

    @NotNull(message = "Active status must be specified")
    private Boolean active;

    private ZonedDateTime createdAt;

    private ZonedDateTime updatedAt;
}
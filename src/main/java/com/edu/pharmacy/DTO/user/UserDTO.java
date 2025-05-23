package com.edu.pharmacy.DTO.user;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object (DTO) representing a user.
 * This class is used to transfer user-related data between different layers of the application.
 * It uses Lombok annotations for boilerplate code generation.
 */
@Data
@AllArgsConstructor
public class UserDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private int age;
    private String address;
    private String email;
    private String password;
}
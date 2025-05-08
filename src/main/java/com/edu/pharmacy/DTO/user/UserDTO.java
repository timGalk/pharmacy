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

    /**
     * The unique identifier for the user.
     */
    private Long id;

    /**
     * The first name of the user.
     */
    private String firstName;

    /**
     * The last name of the user.
     */
    private String lastName;

    /**
     * The unique username of the user.
     */
    private String username;

    /**
     * The age of the user.
     */
    private int age;

    /**
     * The address of the user.
     */
    private String address;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The password of the user.
     */
    private String password;
}
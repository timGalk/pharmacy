package com.edu.pharmacy.entity;

import com.edu.pharmacy.common.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a user in the system.
 * This class is mapped to the "users" table in the database.
 * It uses Lombok annotations for boilerplate code generation.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    /**
     * The unique identifier for the user.
     * This field is auto-generated using the IDENTITY strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The first name of the user.
     * This field is mandatory and cannot be null.
     */
    @Column(unique = false, nullable = false)
    private String firstName;

    /**
     * The last name of the user.
     * This field is mandatory and cannot be null.
     */
    @Column(unique = false, nullable = false)
    private String lastName;

    /**
     * The unique username of the user.
     * This field is mandatory, cannot be null, and must be unique.
     */
    @Column(unique = true, nullable = false)
    private String username;

    /**
     * The address of the user.
     * This field is mandatory and cannot be null.
     */
    @Column(unique = false, nullable = false)
    private String address;

    /**
     * The age of the user.
     * This field is mandatory and cannot be null.
     */
    @Column(unique = false, nullable = false)
    private int age;

    /**
     * The unique email address of the user.
     * This field is mandatory, cannot be null, and must be unique.
     */
    @Column(unique = true, nullable = false)
    private String email;

    /**
     * The password of the user.
     * This field is mandatory and cannot be null.
     */
    @Column(unique = false, nullable = false)
    private String password;
    /**
     * The role assigned to the user.
     * This field represents the user's role within the system, such as administrator or standard user.
     */
    @Column(nullable = false)
    private Role role;

}
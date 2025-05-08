package com.edu.pharmacy.common;

/**
 * Enum representing the roles available in the application.
 * <p>
 * This enum defines two roles:
 * <ul>
 *   <li>ROLE_ADMIN - Represents an administrator with elevated privileges.</li>
 *   <li>ROLE_USER - Represents a standard user with limited access.</li>
 * </ul>
 */
public enum Role {
    /**
     * Administrator role with elevated privileges.
     */
    ROLE_ADMIN,

    /**
     * Standard user role with limited access.
     */
    ROLE_USER,
}
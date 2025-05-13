package com.edu.pharmacy.DTO.user;

import com.edu.pharmacy.common.Role;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class CreateUserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;

}

package com.edu.pharmacy.DTO.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserDTO {
    @Size(min = 2, max = 55)
    private String firstName;
    @Size(min = 2, max = 55)
    private String lastName;
    @Email
    private String email;
    @Min(18)
    private int age;
    @Size(min = 8, max = 20)
    private String password;
    @Size(min = 8, max = 20)
    private String phone;
    private String address;

}

package com.edu.pharmacy.controller;

import com.edu.pharmacy.DTO.user.UserDTO;
import com.edu.pharmacy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/id")
    public UserDTO getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}

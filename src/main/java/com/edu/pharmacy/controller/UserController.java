package com.edu.pharmacy.controller;

import com.edu.pharmacy.DTO.user.UserCreateDTO;
import com.edu.pharmacy.DTO.user.UserDTO;
import com.edu.pharmacy.security.dto.LoginDTO;
import com.edu.pharmacy.security.dto.TokenDTO;
import com.edu.pharmacy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.Token;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return ResponseEntity.ok(userService.register(userCreateDTO));
    }

    @PostMapping("/login")

//    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
//        return ResponseEntity.ok(userService.login(loginDTO));
//    }
    @GetMapping("/my-profile")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}

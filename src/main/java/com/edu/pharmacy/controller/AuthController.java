package com.edu.pharmacy.controller;

import com.edu.pharmacy.DTO.user.UserCreateDTO;
import com.edu.pharmacy.DTO.user.UserDTO;
import com.edu.pharmacy.security.dto.LoginDTO;
import com.edu.pharmacy.security.dto.TokenDTO;
import com.edu.pharmacy.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserCreateDTO userCreateDTO) {
        return ResponseEntity.ok(userService.register(userCreateDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        log.info("Endpoint /me called");
        UserDTO userDTO = userService.getCurrentUser();
        log.info("UserDTO returned: {}", userDTO);
        return ResponseEntity.ok(userDTO);
    }

}

package com.edu.pharmacy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("Public endpoint works!");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<String> userEndpoint(Authentication auth) {
        log.info("User endpoint accessed by: {}, authorities: {}",
                auth.getPrincipal(), auth.getAuthorities());
        return ResponseEntity.ok("User endpoint works! User: " + auth.getPrincipal());
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> adminEndpoint(Authentication auth) {
        log.info("Admin endpoint accessed by: {}, authorities: {}",
                auth.getPrincipal(), auth.getAuthorities());
        return ResponseEntity.ok("Admin endpoint works! User: " + auth.getPrincipal());
    }

    @GetMapping("/auth-info")
    public ResponseEntity<?> authInfo(Authentication auth) {
        if (auth == null) {
            return ResponseEntity.ok("No authentication");
        }

        Map<String, Object> info = new HashMap<>();
        info.put("principal", auth.getPrincipal());
        info.put("authorities", auth.getAuthorities());
        info.put("authenticated", auth.isAuthenticated());

        return ResponseEntity.ok(info);
    }
}
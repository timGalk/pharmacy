package com.edu.pharmacy.security.user;

import com.edu.pharmacy.common.Role;

import java.util.Set;


public record AuthUser(Long userId, Set<Role> roles) {}
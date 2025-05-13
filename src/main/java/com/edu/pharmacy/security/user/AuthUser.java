package com.edu.pharmacy.security.user;

import com.edu.pharmacy.common.Role;

import java.util.List;

public record AuthUser(String userId, List<Role> roles) {}
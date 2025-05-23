package com.edu.pharmacy.security.user;

import com.edu.pharmacy.common.Role;
import lombok.Data;

import java.util.List;


public record AuthUser(Long userId, List<Role> roles) {}
package com.edu.pharmacy.service;

import com.edu.pharmacy.DTO.user.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO loadUserByEmail(String email);
    void saveUser(UserDTO userDTO);
}

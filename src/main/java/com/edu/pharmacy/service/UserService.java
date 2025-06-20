package com.edu.pharmacy.service;

import com.edu.pharmacy.DTO.user.UserCreateDTO;
import com.edu.pharmacy.DTO.user.UserDTO;
import com.edu.pharmacy.security.dto.LoginDTO;
import com.edu.pharmacy.security.dto.TokenDTO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDTO getUser(Long id);

    UserDTO register(UserCreateDTO userCreateDTO);
    UserDTO updateUser(UserDTO userDTO);
    UserDTO getCurrentUser();

    TokenDTO login(LoginDTO loginDTO);
}

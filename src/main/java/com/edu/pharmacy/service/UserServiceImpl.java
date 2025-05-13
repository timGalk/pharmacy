package com.edu.pharmacy.service;

import com.edu.pharmacy.DTO.user.UserDTO;
import com.edu.pharmacy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO loadUserByEmail(String email) {
       Optional<UserDTO> user = userRepository.findByEmail(email);
       if (user.isPresent()) {
           return user.get();
       } else {
           throw new RuntimeException("User not found");
       }
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        userRepository.save(userDTO);
    }


}

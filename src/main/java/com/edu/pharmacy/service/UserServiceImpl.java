package com.edu.pharmacy.service;

import com.edu.pharmacy.DTO.user.UserCreateDTO;
import com.edu.pharmacy.DTO.user.UserDTO;
import com.edu.pharmacy.common.Role;
import com.edu.pharmacy.entity.UserEntity;
import com.edu.pharmacy.mapper.UserMapper;
import com.edu.pharmacy.repository.UserRepository;
import com.edu.pharmacy.security.dto.LoginDTO;
import com.edu.pharmacy.security.dto.TokenDTO;
//import com.edu.pharmacy.security.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
//    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO loadUserByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmailContains(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return userMapper.toDto(userEntity);
    }


    @Override
    @Transactional
    public UserDTO register(UserCreateDTO userCreateDTO) {
        UserEntity userEntity = userMapper.fromCreateDto(userCreateDTO);
        if (userRepository.findByEmailContains(userEntity.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }
        userEntity.setFirstName(userCreateDTO.getFirstName());
        userEntity.setLastName(userCreateDTO.getLastName());
        userEntity.setAddress(userCreateDTO.getAddress());
        userEntity.setAge(userCreateDTO.getAge());
        userEntity.setEmail(userCreateDTO.getEmail());
        userEntity.setPhoneNumber(userCreateDTO.getPhoneNumber());
        userEntity.setRoles(userCreateDTO.getRoles(). stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet()));
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setCreatedAt(ZonedDateTime.now());
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }


    @Override
    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity userEntity = userMapper.toEntity(userDTO);
        userEntity.setUpdatedAt(ZonedDateTime.now());
        userRepository.save(userEntity);
        return userMapper.toDto(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getCurrentUser() {
        return null;
    }

//    @Override
//    @Transactional(readOnly = true)
//    public TokenDTO login(LoginDTO loginDTO) {
//        UserEntity userEntity = userRepository.findByEmailContains(loginDTO.mail())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + loginDTO.mail()));
//
//        if (!passwordEncoder.matches(loginDTO.password(), userEntity.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//
//        return new TokenDTO(
//                userEntity.getId(),
//                jwtService.generateToken(userMapper.toAuthDTO(userEntity))
//        );
//    }
}


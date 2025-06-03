package com.edu.pharmacy.mapper;

import com.edu.pharmacy.DTO.user.UserCreateDTO;
import com.edu.pharmacy.DTO.user.UserDTO;
import com.edu.pharmacy.common.Role;
import com.edu.pharmacy.entity.UserEntity;
import com.edu.pharmacy.security.dto.LoginDTO;
import com.edu.pharmacy.security.user.AuthUser;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class UserMapper {

    public UserDTO toDto(UserEntity entity) {
        if (entity == null) return null;

        UserDTO dto = new UserDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAddress(entity.getAddress());
        dto.setAge(entity.getAge());
        dto.setEmail(entity.getEmail());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setRoles(entity.getRoles());
        dto.setActive(entity.getActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public UserEntity toEntity(UserDTO dto) {
        if (dto == null) return null;

        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAddress(dto.getAddress());
        entity.setAge(dto.getAge());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setRoles(dto.getRoles());
        entity.setActive(dto.getActive());
        entity.setCreatedAt(dto.getCreatedAt());
        entity.setUpdatedAt(dto.getUpdatedAt());

        return entity;
    }

    public UserEntity fromCreateDto(UserCreateDTO createDTO) {
        if (createDTO == null) return null;

        UserEntity entity = new UserEntity();
        entity.setFirstName(createDTO.getFirstName());
        entity.setLastName(createDTO.getLastName());
        entity.setAddress(createDTO.getAddress());
        entity.setAge(createDTO.getAge());
        entity.setEmail(createDTO.getEmail());
        entity.setPassword(createDTO.getPassword());

        // Convert Set<String> to Set<Role>
        if (createDTO.getRoles() != null) {
            Set<Role> roleSet = createDTO.getRoles().stream()
                    .map(String::toUpperCase)
                    .map(Role::valueOf)
                    .collect(Collectors.toSet());
            entity.setRoles(roleSet);
        } else {
            entity.setRoles(new HashSet<>());
        }

        // Optional: set timestamps manually or rely on @PrePersist
        entity.setCreatedAt(ZonedDateTime.now());
        entity.setUpdatedAt(ZonedDateTime.now());

        return entity;
    }

    public AuthUser toAuthDTO(UserEntity userEntity) {
        return new AuthUser(userEntity.getId(),userEntity.getEmail(), userEntity.getRoles());
    }

}
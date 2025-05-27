package com.edu.pharmacy.repository;

import com.edu.pharmacy.DTO.user.UserDTO;
import com.edu.pharmacy.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByEmailContains(String email);

}

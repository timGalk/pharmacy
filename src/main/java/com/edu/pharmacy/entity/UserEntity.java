package com.edu.pharmacy.entity;

import com.edu.pharmacy.common.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.Set;


@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column
    private String address;

    @Column(nullable = false)
    private int age;

    @Column(unique = true, nullable = false)
    private String email;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Column(nullable = false)
    private String password;

    @Column(length = 20)
    private String phoneNumber;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column(nullable = false)
    private Boolean active = true;

    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    @PrePersist
    private void onPrePersist() {
        createdAt = ZonedDateTime.now();
        updatedAt = ZonedDateTime.now();
    }

    @PreUpdate
    private void onPreUpdate() {
        updatedAt = ZonedDateTime.now();
    }
}

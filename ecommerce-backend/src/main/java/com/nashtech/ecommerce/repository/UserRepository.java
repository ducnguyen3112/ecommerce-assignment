package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    Page<User> findByStatusAndFullNameContaining(String name, int status,
                                                 Pageable pageable);

    Page<User> findByFullNameContaining(String name, Pageable pageable);

    Page<User> findByStatus(int status, Pageable pageable);
}

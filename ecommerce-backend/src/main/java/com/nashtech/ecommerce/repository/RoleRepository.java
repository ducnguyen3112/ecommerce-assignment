package com.nashtech.ecommerce.repository;

import com.nashtech.ecommerce.entity.Role;
import com.nashtech.ecommerce.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}

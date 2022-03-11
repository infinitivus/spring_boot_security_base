package com.infinitivus.project.spring_boot_security_base.repository.security_repository;

import com.infinitivus.project.spring_boot_security_base.entity.security.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<UserRole, Integer> {

    @Query("SELECT r FROM UserRole r WHERE r.role = ?1")
    UserRole findByRole(String role);
}

package com.infinitivus.project.spring_boot_security_base.repository.security_repository;

import com.infinitivus.project.spring_boot_security_base.entity.security.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserData,Integer> {

        UserData findByUsername(String username);
}

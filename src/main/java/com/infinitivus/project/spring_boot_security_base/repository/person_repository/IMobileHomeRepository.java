package com.infinitivus.project.spring_boot_security_base.repository.person_repository;

import com.infinitivus.project.spring_boot_security_base.entity.person.MobileHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMobileHomeRepository extends JpaRepository<MobileHome,Integer> {
}

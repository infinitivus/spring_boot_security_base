package com.infinitivus.project.spring_boot_security_base.repository.person_repository;

import com.infinitivus.project.spring_boot_security_base.entity.person.RepairWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepairWorkRepository extends JpaRepository<RepairWork,Integer> {
}

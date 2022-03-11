package com.infinitivus.project.spring_boot_security_base.repository.person_repository;

import com.infinitivus.project.spring_boot_security_base.entity.person.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository extends JpaRepository<Person,Integer> {

    List<Person> findByNameOrSurnameOrEmailOrPhoneNumberOrBirthday
            (String name,String surname,String email,String phoneNumber,String birthday);
}
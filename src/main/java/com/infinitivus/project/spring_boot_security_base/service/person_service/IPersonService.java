package com.infinitivus.project.spring_boot_security_base.service.person_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.infinitivus.project.spring_boot_security_base.entity.person.Person;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

public interface IPersonService {
@Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_MASTER"})
    Person savePerson(Person person);

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_MASTER"})
    Person getPerson(Integer id);

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_MASTER"})
    List<Person> allPerson();

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_MASTER"})
   List<Person> searchPerson(String line);

    @Secured({"ROLE_ADMIN","ROLE_MANAGER","ROLE_MASTER"})
    List<Person> sortPerson(String field);

    @Secured({"ROLE_ADMIN"})
    void deletePerson(Integer id);

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    Person updatePerson(Integer id, JsonPatch patch) throws JsonPatchException, JsonProcessingException;

    @Secured({"ROLE_ADMIN","ROLE_MANAGER"})
   Person applyPatchToPerson(JsonPatch patch, Person person) throws JsonPatchException, JsonProcessingException;
}

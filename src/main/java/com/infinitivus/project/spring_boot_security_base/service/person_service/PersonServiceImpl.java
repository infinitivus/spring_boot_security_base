package com.infinitivus.project.spring_boot_security_base.service.person_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.infinitivus.project.spring_boot_security_base.entity.person.Person;
import com.infinitivus.project.spring_boot_security_base.repository.person_repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService{

    @Autowired
    private IPersonRepository personRepository;

    @Override
    public List<Person> allPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person savePerson(Person person) {
        return  personRepository.save(person);
    }

    @Override
    public Person getPerson(Integer id) {
        return personRepository.findById(id).get();
    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<Person> searchPerson(String line) {
        return personRepository.findByNameOrSurnameOrEmailOrPhoneNumberOrBirthday(line,line,line,line,line);
    }

    @Override
    public List<Person> sortPerson(String field) {
        return personRepository.findAll(Sort.by(field));
    }

    @Override
    public Person updatePerson(Integer id,JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Person person = getPerson(id);
        Person personPatched=applyPatchToPerson(patch,person);
        return personRepository.save(personPatched);
    }

    public Person applyPatchToPerson(JsonPatch patch, Person person) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(person, JsonNode.class));
        return objectMapper.treeToValue(patched, Person.class);
    }
}

package com.infinitivus.project.spring_boot_security_base.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.infinitivus.project.spring_boot_security_base.entity.person.Person;
import com.infinitivus.project.spring_boot_security_base.service.person_service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private IPersonService personService;

    // Creating a new client
//     POST http://localhost:8080/persons/create
//     {"name":"AAAAA","surname":"AAAAAA","mobileHome":{"model":"ZZZZZZ","brand":"ZZZZZZ"}}
    @PostMapping("/create")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        ResponseEntity<String> resp;
        try {
            personService.savePerson(person);
            resp = new ResponseEntity<>(
                    "Person '" + person.getId() + "' created:", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to save person",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Output of all clients
//    GET http://localhost:8080/persons/getAll
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllPerson() {
        ResponseEntity<?> resp;
        try {
            List<Person> pers = personService.allPerson();
            resp = new ResponseEntity<>(pers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all person",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Output of all sorted clients by field(name,surname,email)
//     GET http://localhost:8080/persons/sort/surname
    @GetMapping("/sort/{line}")
    public ResponseEntity<?> getAllSortPerson(@PathVariable String line) {
        ResponseEntity<?> resp;
        try {
            List<Person> list = personService.sortPerson(line);
            resp = new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all sort person",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Search for a client by the line
//  GET  http://localhost:8080/persons/search/alex
    @GetMapping("/search/{line}")
    public ResponseEntity<?> getAllSearchPerson(@PathVariable String line) {
        ResponseEntity<?> resp;
        try {
            List<Person> list = personService.searchPerson(line);
            resp = new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to get all search person",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Getting one client by id
//     GET http://localhost:8080/persons/getPerson/1
    @GetMapping("/getPerson/{id}")
    public ResponseEntity<?> getOnePerson(@PathVariable Integer id) {
        ResponseEntity<?> resp;
        try {
            Person person = personService.getPerson(id);
            resp = new ResponseEntity<>(person, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to find person",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // Deleting the client
//     DELETE http://localhost:8080/persons/remove/1
    @DeleteMapping("remove/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Integer id) {
        ResponseEntity<String> resp;
        try {
            personService.deletePerson(id);
            resp = new ResponseEntity<>(
                    "Person '" + id + "' deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to delete person", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }

    // changing client data by id
//    PATCH http://localhost:8080/persons/modify/1
//     [{"op":"replace","path":"/name","value":"BBBBBB"}]
    @PatchMapping(path = "/modify/{id}")
    public ResponseEntity<String> updatePerson(@PathVariable Integer id, @RequestBody JsonPatch patch) {
        ResponseEntity<String> resp ;
        try {
            Person person= personService.updatePerson(id,patch);
            resp = new ResponseEntity<>(
                    "Person '" + person.getId() + "' updated:",
                    HttpStatus.PARTIAL_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<>(
                    "Unable to update person",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return resp;
    }
}

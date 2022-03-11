package com.infinitivus.project.spring_boot_security_base.controller;

import com.infinitivus.project.spring_boot_security_base.entity.security.UserData;
import com.infinitivus.project.spring_boot_security_base.service.security_service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SecurityController {

    @Autowired
    private IUserService userService;

    //Creating a new user with the User role
// POST http://localhost:8080/registration
//     {"username":"USER","password":"USER"}
    @PostMapping("/registration")
    public ResponseEntity<String> createUser(@RequestBody UserData user) {
        ResponseEntity<String> resp = null;
        try {
            Integer id = userService.registrationUser(user);
            resp = new ResponseEntity<String>(
                    "User '" + id + "' created", HttpStatus.CREATED); //201
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to save user",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        return resp;
    }

    // Output of all users with roles to the console
// GET http://localhost:8080/userDatas/getAll
    @GetMapping("/userDatas/getAll")
    public ResponseEntity<?> getAllUser() {
        ResponseEntity<?> resp = null;
        try {
            List<UserData> list = userService.listAllUser();
            resp = new ResponseEntity<List<UserData>>(list, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to get all user",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    // getting one user by id
// GET http://localhost:8080/userDatas/getUser/1
    @GetMapping("/userDatas/getUser/{id}")
    public ResponseEntity<?> getOneInvoice(@PathVariable Integer id) {
        ResponseEntity<?> resp = null;
        try {
            UserData user = userService.getUser(id);
            resp = new ResponseEntity<UserData>(user, HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to find user",
                    HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    //   Deleting a user
//DELETE http://localhost:8080/userDatas/remove/1
    @DeleteMapping("/userDatas/remove/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        ResponseEntity<String> resp = null;
        try {
            userService.deleteUser(id);
            resp = new ResponseEntity<String>(
                    "Users '" + id + "' deleted", HttpStatus.OK);//200
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to delete user", HttpStatus.INTERNAL_SERVER_ERROR);//500
        }
        return resp;
    }

    //  assigning a role to a user by ID
//PATCH http://localhost:8080/userDatas/modify/3/ROLE_ADMIN
    @PatchMapping("/userDatas/modify/{id}/{role}")
    public ResponseEntity<String> modifyUser(@PathVariable Integer id, @PathVariable String role) {
        ResponseEntity<String> resp = null;
        try {
            userService.updateRoleToUser(id, role);
            resp = new ResponseEntity<String>(
                    "User '" + id + "' updated role",
                    HttpStatus.PARTIAL_CONTENT); //206
        } catch (Exception e) {
            e.printStackTrace();
            resp = new ResponseEntity<String>(
                    "Unable to update user",
                    HttpStatus.INTERNAL_SERVER_ERROR); //500
        }
        return resp;
    }
}



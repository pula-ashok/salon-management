package com.pula.controller;

import com.pula.model.User;
import com.pula.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/msapi/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User createdUser =userService.createUser(user);
        return new  ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/msapi/users")
    public ResponseEntity<List<User>> getUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("/msapi/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws Exception {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PutMapping("/msapi/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id ,@RequestBody User user) throws Exception {
        User updateUser = userService.updateUser(id,user);
        return new ResponseEntity<>(updateUser,HttpStatus.OK);
    }

    @DeleteMapping("/msapi/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws Exception {
        String s = userService.deleteUser(id);
        return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
    }
}

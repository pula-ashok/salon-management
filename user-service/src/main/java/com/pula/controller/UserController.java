package com.pula.controller;

import com.pula.model.User;
import com.pula.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/msapi/users")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }

    @GetMapping("/msapi/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/msapi/users/{id}")
    public User getUserById(@PathVariable Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new Exception("User not found with this id "+id);
    }
    @PutMapping("/msapi/users/{id}")
    public User updateUser(@PathVariable Long id ,@RequestBody User user) throws Exception {
        Optional<User> existingUser = userRepository.findById(id);
        if(existingUser.isEmpty()){
            throw new Exception("User not found with this id "+id);
        }
        User updatedUser = existingUser.get();
        updatedUser.setFullName(user.getFullName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setRole(user.getRole());
        updatedUser.setPhone(user.getPhone());
        return userRepository.save(updatedUser);
    }

    @DeleteMapping("/msapi/users/{id}")
    public String deleteUser(@PathVariable Long id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            userRepository.deleteById(id);
            return "User deleted";
        }
        throw new Exception("User is not found with this id "+id);
    }
}

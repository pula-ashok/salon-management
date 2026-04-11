package com.pula.controller;

import com.pula.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/msapi/users")
    public User getUsers(){
        User user = new User();
        user.setFullName("ashokpula");
        user.setEmail("ashok@gmail.com");
        user.setRole("Customer");
        user.setPhone("9873242342");
        return user;
    }

}

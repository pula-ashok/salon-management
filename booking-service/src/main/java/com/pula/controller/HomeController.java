package com.pula.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ResponseEntity<String> getHomeHandler(){
        return ResponseEntity.ok().body("Booking service for salon booking system");
    }
}

package com.example.springbootcrud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ApiController {

    // Simple GET Endpoint 1 - Root welcome
    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Boot Application! Endpoints available with CI/CD Pipeline: /api/hello, /api/status, /api/greet, /api/users";
    }

    // Simple GET Endpoint 2 - Basic string response
    @GetMapping("/api/hello")
    public String sayHello() {
        return "Hello! Spring Boot basic GET endpoint is working perfectly With CI/CD Pipeline.";
    }

    // Simple GET Endpoint 3 - Status string response
    @GetMapping("/api/status")
    public String getStatus() {
        return "Application Status: UP & Running smoothly with CI/CD Pipeline at " + LocalDateTime.now();
    }

    // Simple GET Endpoint 4 - Parameterized string response
    @GetMapping("/api/greet")
    public String greetUser(@RequestParam(defaultValue = "Guest") String name) {
        return "Namaste / Welcome, " + name + "! Have a great day! With CI/CD Pipeline";
    }
}

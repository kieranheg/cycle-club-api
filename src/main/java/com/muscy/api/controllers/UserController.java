package com.muscy.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/users")
    public String users() {
        System.out.println(">>>> IN /users");
        return "greeting";
    }
}

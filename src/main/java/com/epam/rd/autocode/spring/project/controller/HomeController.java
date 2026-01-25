package com.epam.rd.autocode.spring.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping("/search")
    public ResponseEntity search() {
        return ResponseEntity.ok().body("Hello World");
    }

    @GetMapping("/bestSellers")
    public ResponseEntity bestSellers() {
        return ResponseEntity.ok().body("Hello World");
    }

    @GetMapping("/recommendation")
    public ResponseEntity recommendation() {
        return ResponseEntity.ok().body("Hello World");
    }



}

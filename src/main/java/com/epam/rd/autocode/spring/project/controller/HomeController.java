package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.service.BookService;
import com.epam.rd.autocode.spring.project.service.UserService;
import com.epam.rd.autocode.spring.project.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final BookService bookService;

    @GetMapping
    public String homeLoad (){

        return "home";
    }



    @GetMapping("/bestSellers")
    public ResponseEntity bestSellers() {
        return ResponseEntity.ok().body("Hello World");
    }

    @GetMapping("/new")
    public ResponseEntity newArrivals() {
        return ResponseEntity.ok().body("Hello World");
    }

    @GetMapping("/forChild")
    public ResponseEntity forChild() {
        return ResponseEntity.ok().body("Hello World");
    }



}

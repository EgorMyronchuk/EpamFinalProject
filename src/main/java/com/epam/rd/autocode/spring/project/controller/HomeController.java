package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.response.book.BookRes;
import com.epam.rd.autocode.spring.project.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final BookService bookService;

    @GetMapping
    public String homeLoad(org.springframework.ui.Model model) {
        Pageable sortById = PageRequest.of(0, 10, Sort.by("id").descending());
        
        List<BookRes> bestSellers = bookService.findBestSellers(sortById);
        List<BookRes> newArrivals = bookService.findNew(sortById);
        List<BookRes> forChild = bookService.findForChild(sortById);

        model.addAttribute("bestSellers", bestSellers);
        model.addAttribute("newArrivals", newArrivals);
        model.addAttribute("forChild", forChild);

        return "home";
    }

}

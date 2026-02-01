package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.response.book.BookRes;
import com.epam.rd.autocode.spring.project.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        Pageable limitTen = PageRequest.of(0, 10);

        // Собираем все данные сразу
        List<BookRes> bestSellers = bookService.findBestSellers(limitTen);
        List<BookRes> newArrivals = bookService.findNew(limitTen);
        List<BookRes> forChild = bookService.findForChild(limitTen);

        // Добавляем в модель под разными именами
        model.addAttribute("bestSellers", bestSellers);
        model.addAttribute("newArrivals", newArrivals);
        model.addAttribute("forChild", forChild);

        return "home";
    }

}

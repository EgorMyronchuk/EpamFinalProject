package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.dto.filterDTO.BookSearchFilter;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;
    private final MessageSource messageSource;

    @GetMapping
    public ResponseEntity<List<Book>> allBooks(){
        return ResponseEntity.ok().body(new ArrayList<>());
    }

    @GetMapping("/search")
    public String search(
            @RequestParam("query") String query,
            @RequestParam(value = "fromView", defaultValue = "books-page") String fromView,
            Model model) {

        List<BookDTO> results = bookService.findAllByAuthorAndName(query);

        if (results.isEmpty()) {
            model.addAttribute("loginError", messageSource.getMessage(
                    "search.not_found", new Object[]{query}, LocaleContextHolder.getLocale()));

            if (fromView.equals("books-page")) {
                model.addAttribute("books", bookService.getAllBooks(PageRequest.of(0, 12)).getContent());
            }

            return fromView;
        }

        model.addAttribute("books", results);
        return "books-page";
    }

}

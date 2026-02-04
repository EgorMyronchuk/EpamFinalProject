package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.filterDTO.BookFilter;
import com.epam.rd.autocode.spring.project.dto.response.book.BookFullResp;
import com.epam.rd.autocode.spring.project.dto.response.book.BookRes;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import com.epam.rd.autocode.spring.project.service.BookService;
import com.epam.rd.autocode.spring.project.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final MessageSource messageSource;

    @GetMapping
    public String allBooks(@ModelAttribute BookFilter filter, Model model) {
        Pageable pageable = PageRequest.of(0, filter.getSize());
        Page<BookRes> bookPage = bookService.getFilteredBooks(filter, pageable);

        if (bookPage.isEmpty() && filter.getQuery() != null) {
            model.addAttribute("loginError", messageSource.getMessage(
                    "search.not_found", new Object[]{filter.getQuery()}, LocaleContextHolder.getLocale()));
        }

        model.addAttribute("books", bookPage.getContent());
        model.addAttribute("filter", filter);
        model.addAttribute("hasNext", bookPage.hasNext());
        model.addAttribute("allGenres", bookService.getUniqueGenres());
        model.addAttribute("ageGroups", AgeGroup.values());
        model.addAttribute("languages", Language.values());

        return "book-page";
    }

    @GetMapping("/{id}")
    public String getBookDetails(@PathVariable Long id, Model model) {
        BookFullResp book = bookService.getBookFull(id);
        model.addAttribute("book", book);
        model.addAttribute("bookId", id); // ID нужен для формы добавления в корзину
        return "book-details";
    }

}
package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.dto.filterDTO.BookSearchFilter;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookServiceImpl bookService;

    @GetMapping
    public ResponseEntity<List<Book>> allBooks(){
        return ResponseEntity.ok().body(new ArrayList<>());
    }

    @GetMapping("/books/search")
    public ResponseEntity<Page<BookDTO>> searchBooks(
            BookSearchFilter filter,
            @PageableDefault(size = 10, sort = "title") Pageable pageable
    ) {
        Page<BookDTO> books = bookService.search(filter, pageable);
        return ResponseEntity.ok(books);
    }


}

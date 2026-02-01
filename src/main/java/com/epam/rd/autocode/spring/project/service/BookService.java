package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.request.book.BookReq;
import com.epam.rd.autocode.spring.project.dto.response.book.BookRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Page<BookRes> getAllBooks(Pageable pageable);

    BookRes getBookByName(String name);

    BookRes updateBookByName(String name, BookReq book);

    void deleteBookByName(String name);

    BookRes addBook(BookReq book);

    List<BookRes> findAllByAuthorAndName(String author);

    List<BookRes> findBestSellers(Pageable pageable);

    List<BookRes> findForChild(Pageable pageable);

    List<BookRes> findNew(Pageable pageable);

}

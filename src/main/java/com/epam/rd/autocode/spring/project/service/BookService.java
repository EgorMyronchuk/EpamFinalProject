package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<BookDTO> getAllBooks();

    BookDTO getBookByName(String name);

    BookDTO updateBookByName(String name, BookDTO book);

    void deleteBookByName(String name);

    BookDTO addBook(BookDTO book);

    List<BookDTO> findAllByAuthorAndName(String author);

    Page<BookDTO> findBestSellers(Pageable pageable);

    Page<BookDTO> findForChild(Pageable pageable);

    Page<BookDTO> findNew(Pageable pageable);

}

package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.filterDTO.BookFilter;
import com.epam.rd.autocode.spring.project.dto.mapper.BookMapper;
import com.epam.rd.autocode.spring.project.dto.request.book.BookReq;
import com.epam.rd.autocode.spring.project.dto.response.book.BookFullResp;
import com.epam.rd.autocode.spring.project.dto.response.book.BookRes;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.repo.BookRepository;
import com.epam.rd.autocode.spring.project.repo.specification.BookSpecifications;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private BookSpecifications bookSpecifications;

    @InjectMocks
    private BookServiceImpl bookService;

    private Book book;
    private BookRes bookRes;
    private final Long bookId = 1L;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(bookId);
        book.setName("Clean Code");

        bookRes = new BookRes();
        bookRes.setId(bookId);
        bookRes.setName("Clean Code");
    }

    @Test
    void addBook_ShouldReturnSavedBookRes() {
        BookReq req = new BookReq();
        when(bookMapper.toEntity(req)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookRes);

        BookRes result = bookService.addBook(req);

        assertNotNull(result);
        assertEquals("Clean Code", result.getName());
        verify(bookRepository).save(any());
    }

    @Test
    void updateBookById_ShouldUpdateExistingBook() {
        BookReq req = new BookReq();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookRes);

        BookRes result = bookService.updateBookById(bookId, req);

        verify(bookMapper).updateBookFromDto(req, book);
        assertNotNull(result);
    }

    @Test
    void deleteBookByName_ShouldInvokeDelete_WhenExists() {
        String name = "Clean Code";
        when(bookRepository.findByName(name)).thenReturn(Optional.of(book));

        bookService.deleteBookByName(name);

        verify(bookRepository).delete(book);
    }

    @Test
    void findBestSellers_ShouldReturnList() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Book> page = new PageImpl<>(List.of(book));

        when(bookRepository.findAllByOrderBySoldAmountDesc(pageable)).thenReturn(page);
        when(bookMapper.toDto(book)).thenReturn(bookRes);

        List<BookRes> result = bookService.findBestSellers(pageable);

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void getFilteredBooks_ShouldApplySpecifications() {
        BookFilter filter = new BookFilter();
        Pageable pageable = PageRequest.of(0, 12);
        Page<Book> page = new PageImpl<>(List.of(book));

        when(bookSpecifications.search(any())).thenReturn(null);
        when(bookSpecifications.hasGenres(any())).thenReturn(null);
        when(bookSpecifications.hasAgeGroups(any())).thenReturn(null);
        when(bookSpecifications.hasLanguages(any())).thenReturn(null);
        when(bookSpecifications.priceBetween(any(), any())).thenReturn(null);

        when(bookRepository.findAll(any(Specification.class), eq(pageable))).thenReturn(page);
        when(bookMapper.toDto(any())).thenReturn(bookRes);

        Page<BookRes> result = bookService.getFilteredBooks(filter, pageable);

        assertNotNull(result);
        verify(bookRepository).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    void getBookFull_ShouldReturnFullResponse() {
        BookFullResp fullResp = new BookFullResp();
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookMapper.toFullResp(book)).thenReturn(fullResp);

        BookFullResp result = bookService.getBookFull(bookId);

        assertNotNull(result);
        verify(bookRepository).findById(bookId);
    }

    @Test
    void getUniqueGenres_ShouldReturnList() {
        List<String> genres = List.of("Sci-Fi", "Fantasy");
        when(bookRepository.findDistinctGenres()).thenReturn(genres);

        List<String> result = bookService.getUniqueGenres();

        assertEquals(2, result.size());
        assertTrue(result.contains("Sci-Fi"));
    }
}
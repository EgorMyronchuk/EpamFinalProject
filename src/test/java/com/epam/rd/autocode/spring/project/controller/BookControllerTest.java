package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.filterDTO.BookFilter;
import com.epam.rd.autocode.spring.project.dto.response.book.BookFullResp;
import com.epam.rd.autocode.spring.project.dto.response.book.BookRes;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.BookService;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.service.authService.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private MessageSource messageSource;


    @MockBean
    private CartService cartService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ClientService clientService;
    @MockBean
    private JwtService jwtService;

    @Test
    @WithMockUser
    void testAllBooks() throws Exception {

        BookRes bookRes = new BookRes();
        Page<BookRes> page = new PageImpl<>(List.of(bookRes));

        when(bookService.getFilteredBooks(any(BookFilter.class), any(PageRequest.class)))
                .thenReturn(page);
        when(bookService.getUniqueGenres()).thenReturn(List.of("Fantasy"));

        mockMvc.perform(get("/books")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("book-page"))
                .andExpect(model().attributeExists("books"))
                .andExpect(model().attributeExists("allGenres"))
                .andExpect(model().attributeExists("ageGroups"))
                .andExpect(model().attributeExists("languages"));
    }

    @Test
    @WithMockUser
    void testGetBookDetails() throws Exception {
        Long bookId = 1L;
        BookFullResp fullResp = new BookFullResp();
        fullResp.setName("Thinking in Java");
        fullResp.setAuthor("Bruce Eckel");
        fullResp.setGenre("Educational");
        fullResp.setPrice(java.math.BigDecimal.valueOf(45.50));
        fullResp.setLanguage(Language.ENGLISH);

        when(bookService.getBookFull(bookId)).thenReturn(fullResp);

        mockMvc.perform(get("/books/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(view().name("book-details"))
                .andExpect(model().attribute("book", fullResp))
                .andExpect(model().attribute("bookId", bookId))
                .andExpect(model().attributeExists("book"));
    }
}
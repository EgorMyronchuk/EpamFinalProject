package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.request.book.BookReq;
import com.epam.rd.autocode.spring.project.dto.response.book.BookFullResp;
import com.epam.rd.autocode.spring.project.dto.response.book.BookRes;
import com.epam.rd.autocode.spring.project.dto.response.order.OrderRes;
import com.epam.rd.autocode.spring.project.dto.response.user.UserStatusRes;
import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import com.epam.rd.autocode.spring.project.model.enums.OrderStatus;
import com.epam.rd.autocode.spring.project.repo.RefreshTokenRepository;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.*;
import com.epam.rd.autocode.spring.project.service.authService.JwtService;
import com.epam.rd.autocode.spring.project.service.authService.RefreshTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Locale;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StaffController.class)
@AutoConfigureMockMvc
public class StaffControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private CartService cartService;
    @MockBean
    private UserService userService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ClientService clientService;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private RefreshTokenService refreshTokenService;
    @MockBean
    private RefreshTokenRepository refreshTokenRepository;
    @Qualifier("messageSource")
    @MockBean
    private MessageSource messageSource;

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    void staffDashboard_ShouldReturnViewWithAttributes() throws Exception {

        Page<BookRes> booksPage = new PageImpl<>(Collections.emptyList());
        Page<OrderRes> ordersPage = new PageImpl<>(Collections.emptyList());
        Page<UserStatusRes> usersPage = new PageImpl<>(Collections.emptyList());

        when(bookService.getAllBooks(any(Pageable.class))).thenReturn(booksPage);
        when(orderService.getAllOrders(any(Pageable.class))).thenReturn(ordersPage);
        when(userService.getAllUsersWithStaff(any(Pageable.class))).thenReturn(usersPage);

        mockMvc.perform(get("/staff"))
                .andExpect(status().isOk())
                .andExpect(view().name("staff-dashboard"))
                .andExpect(model().attributeExists("booksPage", "ordersPage", "usersPage"));
    }

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    void blockUser_ShouldRedirectToStaff() throws Exception {
        mockMvc.perform(post("/staff/users/block")
                        .param("email", "test@user.com")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/staff"));

        verify(employeeService).blockUserByEmail("test@user.com");
    }

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    void changeOrderStatus_ShouldInvokeServiceAndRedirect() throws Exception {
        mockMvc.perform(post("/staff/orders/change-status")
                        .param("orderId", "1")
                        .param("status", "SHIPPED")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/staff"));

        verify(orderService).changedStatus(1L, OrderStatus.SHIPPED);
    }

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    void processAddBook_WithErrors_ShouldReturnAddBookView() throws Exception {
        mockMvc.perform(post("/staff/books/add")
                        .flashAttr("book", new BookReq())
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("add-book"))
                .andExpect(model().attributeExists("ageGroups", "languages"));

        verifyNoInteractions(bookService);
    }

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    void processAddBook_Success_ShouldRedirect() throws Exception {
        doReturn("Book added successfully")
                .when(messageSource)
                .getMessage(anyString(), any(), any(Locale.class));

        mockMvc.perform(post("/staff/books/add")
                        .param("name", "Spring in Action")
                        .param("author", "Craig Walls")
                        .param("genre", "IT")
                        .param("price", "500.00")
                        .param("pages", "520")
                        .param("publicationDate", "2023-01-01")
                        .param("language", "ENGLISH")
                        .param("ageGroup", "ADULT")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/staff?successMessage=*"));

        verify(bookService).addBook(any(BookReq.class));
    }

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    void showEditBookForm_ShouldReturnEditView() throws Exception {
        BookFullResp mockBook = new BookFullResp();
        when(bookService.getBookFull(1L)).thenReturn(mockBook);

        mockMvc.perform(get("/staff/books/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit-book"))
                .andExpect(model().attribute("bookId", 1L))
                .andExpect(model().attributeExists("ageGroups", "languages", "book"));
    }

    @BeforeEach
    void setUp() {
        doReturn("Success message")
                .when(messageSource)
                .getMessage(anyString(), any(), any(Locale.class));
    }

    @Test
    @WithMockUser(roles = "EMPLOYEE")
    void processUpdateBook_Success_ShouldRedirect() throws Exception {
        Long bookId = 1L;

        mockMvc.perform(post("/staff/books/edit/{id}", bookId)
                        .param("name", "Updated Name")
                        .param("author", "Updated Author")
                        .param("genre", "IT")
                        .param("price", "600.00")
                        .param("pages", "550")
                        .param("publicationDate", "2023-01-01")
                        .param("language", "ENGLISH")
                        .param("ageGroup", "ADULT")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/staff?successMessage=*"));

        verify(bookService).updateBookById(eq(bookId), any(BookReq.class));
    }
}
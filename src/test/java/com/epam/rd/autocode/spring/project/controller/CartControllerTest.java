package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.repo.RefreshTokenRepository;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.UserService;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.service.authService.JwtService;
import com.epam.rd.autocode.spring.project.service.authService.RefreshTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CartService cartService;
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ClientService clientService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private RefreshTokenService refreshTokenService;
    @MockBean
    private RefreshTokenRepository refreshTokenRepository;

    private final String TEST_EMAIL = "user@example.com";
    private final Long TEST_USER_ID = 123L;
    private final Long TEST_BOOK_ID = 1L;

    @Test
    @WithMockUser(username = TEST_EMAIL)
    void addToCart_ShouldRedirectToReferer() throws Exception {
        when(userService.getUserIdByEmail(TEST_EMAIL)).thenReturn(TEST_USER_ID);
        String referer = "http://localhost/books/1";

        mockMvc.perform(post("/cart/add")
                        .param("bookId", TEST_BOOK_ID.toString())
                        .header("Referer", referer)
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(referer));

        verify(cartService).plusOneToCartItem(TEST_BOOK_ID, TEST_USER_ID);
    }

    @Test
    @WithMockUser(username = TEST_EMAIL)
    void addToCart_ShouldRedirectToHome_WhenRefererIsNull() throws Exception {
        when(userService.getUserIdByEmail(TEST_EMAIL)).thenReturn(TEST_USER_ID);

        mockMvc.perform(post("/cart/add")
                        .param("bookId", TEST_BOOK_ID.toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(username = TEST_EMAIL)
    void plusItem_ShouldRedirectToOrders() throws Exception {
        when(userService.getUserIdByEmail(TEST_EMAIL)).thenReturn(TEST_USER_ID);

        mockMvc.perform(post("/cart/plus")
                        .param("bookId", TEST_BOOK_ID.toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders"));

        verify(cartService).plusOneToCartItem(TEST_BOOK_ID, TEST_USER_ID);
    }

    @Test
    @WithMockUser(username = TEST_EMAIL)
    void minusItem_ShouldRedirectToOrders() throws Exception {
        when(userService.getUserIdByEmail(TEST_EMAIL)).thenReturn(TEST_USER_ID);

        mockMvc.perform(post("/cart/minus")
                        .param("bookId", TEST_BOOK_ID.toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders"));

        verify(cartService).minusOneToCartItem(TEST_BOOK_ID, TEST_USER_ID);
    }

    @Test
    @WithMockUser(username = TEST_EMAIL)
    void removeItem_ShouldRedirectToOrders() throws Exception {
        when(userService.getUserIdByEmail(TEST_EMAIL)).thenReturn(TEST_USER_ID);

        mockMvc.perform(post("/cart/remove")
                        .param("bookId", TEST_BOOK_ID.toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders"));

        verify(cartService).removeCartItem(TEST_BOOK_ID, TEST_USER_ID);
    }
}
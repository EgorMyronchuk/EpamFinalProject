package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.response.cart.CartRes;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.OrderService;
import com.epam.rd.autocode.spring.project.service.UserService;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.service.authService.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    @MockBean
    private OrderService orderService;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ClientService clientService;
    @MockBean
    private JwtService jwtService;

    private final String TEST_EMAIL = "test@example.com";
    private final Long TEST_USER_ID = 1L;

    @Test
    @WithMockUser(username = TEST_EMAIL)
    void showCart_ShouldReturnOrdersView() throws Exception {
        CartRes mockCart = new CartRes();
        when(userService.getUserIdByEmail(TEST_EMAIL)).thenReturn(TEST_USER_ID);
        when(cartService.getCart(TEST_USER_ID)).thenReturn(mockCart);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("orders"))
                .andExpect(model().attribute("cart", mockCart));
    }

    @Test
    @WithMockUser(username = TEST_EMAIL)
    void createOrder_Success_ShouldRedirectToHome() throws Exception {
        when(userService.getUserIdByEmail(TEST_EMAIL)).thenReturn(TEST_USER_ID);
        when(orderService.createOrder(TEST_USER_ID)).thenReturn(null);

        mockMvc.perform(post("/orders/create")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/home"))
                .andExpect(flash().attributeExists("successMessage"));

        verify(orderService).createOrder(TEST_USER_ID);
    }

    @Test
    @WithMockUser(username = TEST_EMAIL)
    void createOrder_Exception_ShouldRedirectToOrders() throws Exception {
        when(userService.getUserIdByEmail(TEST_EMAIL)).thenReturn(TEST_USER_ID);
        doThrow(new RuntimeException("Out of stock")).when(orderService).createOrder(TEST_USER_ID);

        mockMvc.perform(post("/orders/create")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/orders"))
                .andExpect(flash().attribute("loginError", "Out of stock"));
    }

    @Test
    @WithMockUser(username = TEST_EMAIL)
    void cancelOrder_Success_ShouldRedirectToProfile() throws Exception {
        Long orderId = 99L;

        mockMvc.perform(post("/orders/delete")
                        .param("orderId", orderId.toString())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/profile"))
                .andExpect(flash().attributeExists("successMessage"));

        verify(orderService).deleteOrder(orderId);
    }
}
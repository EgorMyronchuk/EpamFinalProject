package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.repo.RefreshTokenRepository;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.service.authService.AuthenticationService;
import com.epam.rd.autocode.spring.project.service.authService.CustomUserDetailsService;
import com.epam.rd.autocode.spring.project.service.authService.JwtService;
import com.epam.rd.autocode.spring.project.service.authService.RefreshTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AdminController.class)
@AutoConfigureMockMvc(addFilters = false)
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private MessageSource messageSource;
    @MockBean
    private ClientService clientService;
    @MockBean
    private CartService cartService;
    @MockBean
    private RefreshTokenService refreshTokenService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private RefreshTokenRepository refreshTokenRepository;

    @Test
    void showAdmin_ShouldReturnAdminPageWithAttribute() throws Exception {
        mockMvc.perform(get("/secret/admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-page"))
                .andExpect(model().attributeExists("createUserReq"));
    }

    @Test
    void addEmployee_Success_ShouldRedirectWithSuccessMessage() throws Exception {
        String successMsg = "Employee created successfully";
        when(messageSource.getMessage(eq("success.employee_created"), any(), any(Locale.class)))
                .thenReturn(successMsg);

        mockMvc.perform(post("/secret/admin")
                        .param("email", "employee@test.com")
                        .param("password", "Pass1234")
                        .param("name", "John Doe"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/secret/admin?successMessage=*"));

        verify(authenticationService, times(1)).signUpForEmployee(any(CreateUserReq.class));
    }

    @Test
    void addEmployee_ValidationError_ShouldReturnAdminPage() throws Exception {
        mockMvc.perform(post("/secret/admin")
                        .param("email", "invalid-email")
                        .param("password", "")
                        .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("admin-page"))
                .andExpect(model().hasErrors());

        verify(authenticationService, never()).signUpForEmployee(any());
    }
}
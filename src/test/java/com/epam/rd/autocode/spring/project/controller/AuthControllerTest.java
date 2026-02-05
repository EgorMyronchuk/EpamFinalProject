package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.jwtDTO.JwtAuthenticationResponse;
import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.dto.request.auth.SignInReq;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.service.authService.AuthenticationService;
import com.epam.rd.autocode.spring.project.service.authService.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private CartService cartService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private ClientService clientService;

    @Test
    void showRegistrationForm_ShouldReturnRegisterView() throws Exception {
        mockMvc.perform(get("/auth/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("createUserReq"));
    }

    @Test
    void registration_Success_ShouldRedirectToLogin() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .param("email", "test@test.com")
                        .param("password", "Password123")
                        .param("name", "Ivan"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/login"));

        verify(authService, times(1)).signUpForUser(any(CreateUserReq.class));
    }

    @Test
    void registration_ValidationError_ShouldReturnRegisterForm() throws Exception {
        mockMvc.perform(post("/auth/register")
                        .param("email", "not-an-email")
                        .param("password", "123")
                        .param("name", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().hasErrors());

        verify(authService, never()).signUpForUser(any());
    }

    @Test
    void login_Success_ShouldSetCookieAndRedirectToHome() throws Exception {
        JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse("mocked-jwt-token");
        when(authService.signIn(any(SignInReq.class))).thenReturn(jwtResponse);

        mockMvc.perform(post("/auth/login")
                        .param("email", "user@test.com")
                        .param("password", "Password123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(cookie().value("jwt", "mocked-jwt-token"))
                .andExpect(cookie().httpOnly("jwt", true))
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    void login_ValidationError_ShouldReturnLoginForm() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .param("email", "bad-email")
                        .param("password", ""))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().hasErrors());

        verify(authService, never()).signIn(any());
    }
}
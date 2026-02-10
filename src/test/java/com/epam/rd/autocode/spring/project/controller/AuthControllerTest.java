package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.jwtDTO.JwtAuthenticationResponse;
import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.dto.request.auth.SignInReq;
import com.epam.rd.autocode.spring.project.model.RefreshToken;
import com.epam.rd.autocode.spring.project.model.User;
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
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
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
    private CartService cartService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private ClientService clientService;
    @MockBean
    private RefreshTokenService refreshTokenService;
    @MockBean
    private RefreshTokenRepository refreshTokenRepository;
    @MockBean
    private com.epam.rd.autocode.spring.project.service.authService.CustomUserDetailsService customUserDetailsService;


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
        String email = "user@test.com";
        JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse("mocked-jwt-token");

        when(authService.signIn(any(SignInReq.class))).thenReturn(jwtResponse);

        com.epam.rd.autocode.spring.project.model.RefreshToken mockRefreshToken =
                new com.epam.rd.autocode.spring.project.model.RefreshToken();
        mockRefreshToken.setToken("mocked-refresh-token");
        when(refreshTokenService.createRefreshToken(anyString())).thenReturn(mockRefreshToken);

        UserDetails mockUser = org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password("password")
                .authorities("ROLE_USER")
                .build();

        when(customUserDetailsService.loadUserByUsername(any())).thenReturn(mockUser);

        mockMvc.perform(post("/auth/login")
                        .param("email", email)
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

    @Test
    void showLoginForm_ShouldReturnLoginView() throws Exception {
        mockMvc.perform(get("/auth/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("signInReq"));
    }

    @Test
    void login_SuccessAsAdmin_ShouldRedirectToAdminPage() throws Exception {
        String email = "admin@test.com";
        JwtAuthenticationResponse jwtResponse = new JwtAuthenticationResponse("admin-jwt");
        when(authService.signIn(any(SignInReq.class))).thenReturn(jwtResponse);

        RefreshToken mockRefreshToken =
                new RefreshToken();
        mockRefreshToken.setToken("admin-refresh");
        when(refreshTokenService.createRefreshToken(anyString())).thenReturn(mockRefreshToken);

        UserDetails mockAdmin = org.springframework.security.core.userdetails.User
                .withUsername(email)
                .password("password")
                .authorities("ROLE_ADMIN")
                .build();
        when(customUserDetailsService.loadUserByUsername(any())).thenReturn(mockAdmin);

        mockMvc.perform(post("/auth/login")
                        .param("email", email)
                        .param("password", "Admin123"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/secret/admin"));
    }

    @Test
    void refreshToken_Success_ShouldSetNewJwtCookie() throws Exception {

        String oldRefreshToken = "old-refresh-token";
        String newAccessToken = "new-access-token";

        User user = new User();
        user.setEmail("user@test.com");

        RefreshToken refreshTokenObj =
                new com.epam.rd.autocode.spring.project.model.RefreshToken();
        refreshTokenObj.setUser(user);
        refreshTokenObj.setToken(oldRefreshToken);

        when(refreshTokenRepository.findByToken(oldRefreshToken)).thenReturn(java.util.Optional.of(refreshTokenObj));
        when(refreshTokenService.verifyExpiration(any())).thenReturn(refreshTokenObj);
        when(jwtService.generateToken(any())).thenReturn(newAccessToken);

        mockMvc.perform(post("/auth/refresh")
                        .cookie(new jakarta.servlet.http.Cookie("refresh_token", oldRefreshToken)))
                .andExpect(status().is3xxRedirection())
                .andExpect(cookie().value("jwt", newAccessToken))
                .andExpect(redirectedUrl("/home"));
    }

    @Test
    void refreshToken_TokenNotFound_ShouldRedirectToLogin() throws Exception {
        String invalidToken = "invalid-token";
        when(refreshTokenRepository.findByToken(invalidToken)).thenReturn(java.util.Optional.empty());

        mockMvc.perform(post("/auth/refresh")
                        .cookie(new jakarta.servlet.http.Cookie("refresh_token", invalidToken)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/auth/login"));
    }
}
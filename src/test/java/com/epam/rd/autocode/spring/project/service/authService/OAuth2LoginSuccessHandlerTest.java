package com.epam.rd.autocode.spring.project.service.authService;

import com.epam.rd.autocode.spring.project.model.*;
import com.epam.rd.autocode.spring.project.model.enums.Role;
import com.epam.rd.autocode.spring.project.repo.CartRepository;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OAuth2LoginSuccessHandlerTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private RefreshTokenService refreshTokenService;
    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private OAuth2LoginSuccessHandler successHandler;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private Authentication authentication;
    @Mock
    private OAuth2User oAuth2User;

    private final String email = "test@gmail.com";
    private final String name = "Test User";

    @BeforeEach
    void setUp() {
        when(authentication.getPrincipal()).thenReturn(oAuth2User);
        when(oAuth2User.getAttribute("email")).thenReturn(email);
        when(oAuth2User.getAttribute("name")).thenReturn(name);
    }

    @Test
    void onAuthenticationSuccess_NewUser_ShouldCreateUserAndCart() throws IOException {
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        setupSecurityMocks();

        successHandler.onAuthenticationSuccess(request, response, authentication);

        verify(userRepository).save(any(User.class));
        verify(cartRepository).save(any(Cart.class));

        ArgumentCaptor<Cookie> cookieCaptor = ArgumentCaptor.forClass(Cookie.class);
        verify(response, times(2)).addCookie(cookieCaptor.capture());

        assertTrue(cookieCaptor.getAllValues().stream().anyMatch(c -> c.getName().equals("jwt")));
        assertTrue(cookieCaptor.getAllValues().stream().anyMatch(c -> c.getName().equals("refresh_token")));
    }

    @Test
    void onAuthenticationSuccess_ExistingAdmin_ShouldRedirectToAdminPage() throws IOException {
        User admin = new User();
        admin.setEmail(email);
        admin.setRole(Role.ADMIN);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(admin));
        setupSecurityMocks();

        successHandler.onAuthenticationSuccess(request, response, authentication);

        verify(response).addCookie(argThat(c -> c.getName().equals("jwt")));
    }

    private void setupSecurityMocks() {
        when(jwtService.generateToken(any(UserDetails.class))).thenReturn("mock-jwt");
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken("mock-refresh");
        when(refreshTokenService.createRefreshToken(email)).thenReturn(refreshToken);
    }
}
package com.epam.rd.autocode.spring.project.service.authService;

import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.model.UserPrincipal;
import com.epam.rd.autocode.spring.project.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    // Генерируем тестовый ключ (32 символа в Base64 для HS256)
    private final String secretKey = Base64.getEncoder().encodeToString(
            "my-super-secret-key-that-must-be-long-enough-32-chars".getBytes()
    );

    private UserPrincipal userPrincipal;
    private User user;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "jwtSigningKey", secretKey);

        user = new User();
        user.setId(1L);
        user.setEmail("test@example.com");
        user.setRole(Role.USER);
        user.setActive(true);

        userPrincipal = new UserPrincipal(user);
    }

    @Test
    void generateToken_ShouldReturnValidToken() {
        String token = jwtService.generateToken(userPrincipal);

        assertNotNull(token);
        assertFalse(token.isEmpty());
        assertEquals(user.getEmail(), jwtService.extractEmailName(token));
    }

    @Test
    void extractEmailName_ShouldReturnCorrectEmail() {
        String token = jwtService.generateToken(userPrincipal);

        String extractedEmail = jwtService.extractEmailName(token);

        assertEquals("test@example.com", extractedEmail);
    }

    @Test
    void isTokenValid_ShouldReturnTrueForCorrectUser() {
        String token = jwtService.generateToken(userPrincipal);

        boolean isValid = jwtService.isTokenValid(token, userPrincipal);

        assertTrue(isValid);
    }

    @Test
    void isTokenValid_ShouldReturnFalseForDifferentUser() {
        String token = jwtService.generateToken(userPrincipal);

        User otherUser = new User();
        otherUser.setEmail("wrong@example.com");
        UserPrincipal otherPrincipal = new UserPrincipal(otherUser);

        boolean isValid = jwtService.isTokenValid(token, otherPrincipal);

        assertFalse(isValid);
    }

    @Test
    void extractClaim_ShouldWorkThroughExtractEmailName() {

        String token = jwtService.generateToken(userPrincipal);
        assertDoesNotThrow(() -> jwtService.extractEmailName(token));
    }
}
package com.epam.rd.autocode.spring.project.model;

import com.epam.rd.autocode.spring.project.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UserPrincipalTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("hashed_password");
        user.setRole(Role.USER);
        user.setActive(true);
    }

    @Test
    void constructor_ShouldThrowException_WhenUserIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new UserPrincipal(null));
    }

    @Test
    void getAuthorities_ShouldReturnRoleWithPrefix() {
        UserPrincipal principal = new UserPrincipal(user);

        Collection<? extends GrantedAuthority> authorities = principal.getAuthorities();

        assertEquals(1, authorities.size());
        String authority = authorities.iterator().next().getAuthority();
        assertEquals("ROLE_USER", authority);
    }

    @Test
    void getUsername_ShouldReturnEmail() {
        UserPrincipal principal = new UserPrincipal(user);
        assertEquals("test@example.com", principal.getUsername());
    }

    @Test
    void getPassword_ShouldReturnPassword() {
        UserPrincipal principal = new UserPrincipal(user);
        assertEquals("hashed_password", principal.getPassword());
    }

    @Test
    void isEnabled_ShouldReturnTrue_WhenUserIsActive() {
        user.setActive(true);
        UserPrincipal principal = new UserPrincipal(user);

        assertTrue(principal.isEnabled());
    }

    @Test
    void isEnabled_ShouldReturnFalse_WhenUserIsNotActive() {
        user.setActive(false);
        UserPrincipal principal = new UserPrincipal(user);

        assertFalse(principal.isEnabled());
    }

    @Test
    void booleanMethods_ShouldReturnTrueByDefault() {
        UserPrincipal principal = new UserPrincipal(user);

        assertTrue(principal.isAccountNonExpired());
        assertTrue(principal.isAccountNonLocked());
        assertTrue(principal.isCredentialsNonExpired());
    }

    @Test
    void getEmail_ShouldReturnCorrectEmail() {
        UserPrincipal principal = new UserPrincipal(user);
        assertEquals("test@example.com", principal.getEmail());
    }
}
package com.epam.rd.autocode.spring.project.model;

import com.epam.rd.autocode.spring.project.model.enums.Role;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserFieldAssignments() {
        User user = new User();
        String email = "test@example.com";
        String name = "John Doe";
        String password = "securePassword";
        LocalDateTime deletedAt = LocalDateTime.now();

        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        user.setRole(Role.USER);
        user.setActive(false);
        user.setDeletedAt(deletedAt);

        assertEquals(email, user.getEmail());
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertEquals(Role.USER, user.getRole());
        assertFalse(user.isActive());
        assertEquals(deletedAt, user.getDeletedAt());
    }

    @Test
    void testDefaultValues() {
        User user = new User();

        assertTrue(user.isActive(), "User should be active by default");
    }

    @Test
    void testOneToOneProfiles() {
        User user = new User();
        Client client = new Client();
        Employee employee = new Employee();

        user.setClientProfile(client);
        user.setEmployeeProfile(employee);

        assertNotNull(user.getClientProfile());
        assertNotNull(user.getEmployeeProfile());
        assertEquals(client, user.getClientProfile());
        assertEquals(employee, user.getEmployeeProfile());
    }
}
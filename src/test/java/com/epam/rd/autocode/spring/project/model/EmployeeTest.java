package com.epam.rd.autocode.spring.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    private Employee employee;
    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("employee@company.com");

        employee = new Employee();
    }

    @Test
    void testEmployeeFields() {
        Long id = 100L;
        String phone = "+380991234567";
        LocalDate birthDate = LocalDate.of(1990, 5, 20);

        employee.setId(id);
        employee.setUser(user);
        employee.setPhone(phone);
        employee.setBirthDate(birthDate);

        assertEquals(id, employee.getId());
        assertEquals(user, employee.getUser());
        assertEquals(phone, employee.getPhone());
        assertEquals(birthDate, employee.getBirthDate());
    }

    @Test
    void testConstructorWithUser() {
        Employee newEmployee = new Employee(user);

        assertNotNull(newEmployee.getUser());
        assertEquals(user, newEmployee.getUser());
    }

    @Test
    void testNoArgsConstructor() {
        Employee emptyEmployee = new Employee();
        assertNull(emptyEmployee.getId());
        assertNull(emptyEmployee.getUser());
        assertNull(emptyEmployee.getPhone());
    }

    @Test
    void testAllArgsConstructor() {
        Long id = 1L;
        String phone = "123456";
        LocalDate date = LocalDate.now();

        Employee fullEmployee = new Employee(id, user, phone, date);

        assertEquals(id, fullEmployee.getId());
        assertEquals(user, fullEmployee.getUser());
        assertEquals(phone, fullEmployee.getPhone());
    }
}
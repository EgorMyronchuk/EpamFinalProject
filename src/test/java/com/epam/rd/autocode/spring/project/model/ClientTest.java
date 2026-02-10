package com.epam.rd.autocode.spring.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setEmail("client@test.com");
    }

    @Test
    void testClientFieldsAndLombok() {
        Client client = new Client();
        String phone = "123456789";
        String address = "Main Street 1";
        BigDecimal customBalance = new BigDecimal("100.00");

        client.setId(5L);
        client.setUser(user);
        client.setPhoneNumber(phone);
        client.setDeliveryAddress(address);
        client.setBalance(customBalance);

        assertEquals(5L, client.getId());
        assertEquals(user, client.getUser());
        assertEquals(phone, client.getPhoneNumber());
        assertEquals(address, client.getDeliveryAddress());
        assertEquals(customBalance, client.getBalance());
    }

    @Test
    void testDefaultBalance_ShouldBeZero() {
        Client client = new Client();

        assertNotNull(client.getBalance());
        assertEquals(BigDecimal.ZERO, client.getBalance(), "Balance should be initialized to zero by default");
    }

    @Test
    void testConstructorWithUser() {
        Client client = new Client(user);

        assertNotNull(client.getUser());
        assertEquals(user, client.getUser());

        assertEquals(BigDecimal.ZERO, client.getBalance());
    }
}
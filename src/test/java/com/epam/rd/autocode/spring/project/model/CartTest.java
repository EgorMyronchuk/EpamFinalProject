package com.epam.rd.autocode.spring.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(7L);
        user.setEmail("cart.owner@test.com");
    }

    @Test
    void testCartFieldsAndLombok() {
        Cart cart = new Cart();
        List<CartItem> items = new ArrayList<>();
        items.add(new CartItem());

        cart.setId(1L);
        cart.setUser(user);
        cart.setItems(items);

        assertEquals(1L, cart.getId());
        assertEquals(user, cart.getUser());
        assertEquals(1, cart.getItems().size());
        assertEquals(items, cart.getItems());
    }

    @Test
    void testConstructorWithUser() {
        Cart cart = new Cart(user);

        assertNotNull(cart.getUser());
        assertEquals(user, cart.getUser());
    }

    @Test
    void testItemsListOperations() {
        Cart cart = new Cart(user);
        List<CartItem> items = new ArrayList<>();
        CartItem item = new CartItem();
        item.setCart(cart);
        items.add(item);

        cart.setItems(items);

        assertNotNull(cart.getItems());
        assertEquals(1, cart.getItems().size());
        assertEquals(cart, cart.getItems().get(0).getCart());
    }
}
package com.epam.rd.autocode.spring.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    private CartItem cartItem;
    private Book book;
    private Cart cart;

    @BeforeEach
    void setUp() {
        cartItem = new CartItem();
        book = new Book();
        book.setId(10L);
        book.setName("Test Book");

        cart = new Cart();
        cart.setId(5L);
    }

    @Test
    void testCartItemFieldsAndLombok() {
        Long id = 1L;
        Integer quantity = 3;

        cartItem.setId(id);
        cartItem.setBook(book);
        cartItem.setQuantity(quantity);
        cartItem.setCart(cart);

        assertEquals(id, cartItem.getId());
        assertEquals(book, cartItem.getBook());
        assertEquals(quantity, cartItem.getQuantity());
        assertEquals(cart, cartItem.getCart());
    }

    @Test
    void testNoArgsConstructor() {
        CartItem emptyItem = new CartItem();

        assertNull(emptyItem.getId());
        assertNull(emptyItem.getBook());
        assertNull(emptyItem.getQuantity());
        assertNull(emptyItem.getCart());
    }

    @Test
    void testRelationshipLinks() {
        cartItem.setBook(book);
        cartItem.setCart(cart);

        assertAll("Relationships",
                () -> assertEquals(10L, cartItem.getBook().getId()),
                () -> assertEquals(5L, cartItem.getCart().getId())
        );
    }
}
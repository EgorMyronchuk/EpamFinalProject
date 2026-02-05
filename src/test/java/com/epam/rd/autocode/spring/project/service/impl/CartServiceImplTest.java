package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.response.cart.CartRes;
import com.epam.rd.autocode.spring.project.exception.CartException;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.Cart;
import com.epam.rd.autocode.spring.project.model.CartItem;
import com.epam.rd.autocode.spring.project.repo.BookRepository;
import com.epam.rd.autocode.spring.project.repo.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    private final Long userId = 1L;
    private final Long bookId = 10L;
    private Cart cart;
    private Book book;

    @BeforeEach
    void setUp() {
        cart = new Cart();
        cart.setId(100L);
        cart.setItems(new ArrayList<>());

        book = new Book();
        book.setId(bookId);
        book.setPrice(new BigDecimal("100.00"));
    }

    @Test
    void getCart_ShouldCalculateTotalPriceCorrectly() {
        CartItem item = new CartItem();
        item.setBook(book);
        item.setQuantity(2);
        cart.getItems().add(item);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        CartRes result = cartService.getCart(userId);

        assertEquals(new BigDecimal("200.00"), result.getTotalPrice());
        assertEquals(1, result.getItems().size());
    }

    @Test
    void getCart_ShouldThrowException_WhenNotFound() {
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.empty());

        assertThrows(CartException.class, () -> cartService.getCart(userId));
    }

    @Test
    void getQuantityItemsInCart_ShouldReturnSumOfQuantities() {
        CartItem item1 = new CartItem();
        item1.setQuantity(3);
        CartItem item2 = new CartItem();
        item2.setQuantity(5);
        cart.getItems().addAll(List.of(item1, item2));

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        Long quantity = cartService.getQuantityItemsInCart(userId);

        assertEquals(8L, quantity);
    }

    @Test
    void addCartItem_ShouldIncreaseQuantity_WhenItemExists() {
        CartItem existingItem = new CartItem();
        existingItem.setBook(book);
        existingItem.setQuantity(2);
        cart.getItems().add(existingItem);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        boolean result = cartService.addCartItem(bookId, userId, 1);

        assertTrue(result);
        assertEquals(3, existingItem.getQuantity());
        verify(cartRepository).save(cart);
    }

    @Test
    void addCartItem_ShouldAddNewItem_WhenItemDoesNotExist() {
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        boolean result = cartService.addCartItem(bookId, userId, 1);

        assertTrue(result);
        assertEquals(1, cart.getItems().size());
        assertEquals(bookId, cart.getItems().get(0).getBook().getId());
        verify(cartRepository).save(cart);
    }

    @Test
    void plusOneToCartItem_ShouldCallAddWithPositiveDelta() {
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        cartService.plusOneToCartItem(bookId, userId);

        assertEquals(1, cart.getItems().get(0).getQuantity());
    }

    @Test
    void minusOneToCartItem_ShouldCallAddWithNegativeDelta() {
        CartItem item = new CartItem();
        item.setBook(book);
        item.setQuantity(5);
        cart.getItems().add(item);

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        cartService.minusOneToCartItem(bookId, userId);

        assertEquals(4, item.getQuantity());
    }

    @Test
    void removeCartItem_ShouldRemoveItemFromList() {
        CartItem item = new CartItem();
        item.setBook(book);
        cart.getItems().add(item);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        cartService.removeCartItem(bookId, userId);

        assertTrue(cart.getItems().isEmpty());
        verify(cartRepository).save(cart);
    }
}
package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.request.book.BookReq;
import com.epam.rd.autocode.spring.project.dto.response.cart.CartRes;
import com.epam.rd.autocode.spring.project.exception.CartException;
import com.epam.rd.autocode.spring.project.model.*;
import com.epam.rd.autocode.spring.project.repo.BookRepository;
import com.epam.rd.autocode.spring.project.repo.CartRepository;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;

    @Override
    public CartRes getCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartException("Cart not found"));

        BigDecimal totalPrice = cart.getItems().stream()
                .map(item -> item.getBook().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new CartRes(cart.getItems(), totalPrice);
    }

    public Long getQuantityItemsInCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .map(cart -> cart.getItems().stream()
                        .mapToLong(CartItem::getQuantity)
                        .sum())
                .orElse(0L);
    }


    @Override
    @Transactional
    public boolean addCartItem(Long bookId, Long userId , int delta) {
        // 1. Находим книгу
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        // 2. Получаем корзину пользователя
        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new CartException("NotCurt found"));

        // 3. Проверяем, есть ли уже такая книга в корзине
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst();

        if (existingItem.isPresent()) {
            // Если есть — увеличиваем количество
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + delta);
        } else {
            // Если нет — создаем новый элемент
            CartItem newItem = new CartItem();
            newItem.setBook(book);
            newItem.setQuantity(1);
            newItem.setCart(cart);
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart);
        return true;
    }

    @Override
    @Transactional
    public void plusOneToCartItem(Long bookId, Long userId) {
        System.out.println("Я тут бул плюс");
        addCartItem(bookId, userId, 1);
    }

    @Override
    @Transactional
    public void minusOneToCartItem(Long bookId, Long userId) {
        addCartItem(bookId, userId, -1);
    }

    @Override
    @Transactional
    public void removeCartItem(Long bookId, Long userID) {
        Cart cart = cartRepository.findByUserId(userID)
                .orElseThrow(() -> new CartException("Cart not found"));
        System.out.println("Я тут бул ремув");
        cart.getItems().removeIf(item -> item.getBook().getId().equals(bookId));
        cartRepository.save(cart);
    }

}

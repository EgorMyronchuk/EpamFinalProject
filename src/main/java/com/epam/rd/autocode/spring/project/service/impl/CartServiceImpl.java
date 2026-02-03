package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.request.book.BookReq;
import com.epam.rd.autocode.spring.project.exception.CartException;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.Cart;
import com.epam.rd.autocode.spring.project.model.CartItem;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.repo.BookRepository;
import com.epam.rd.autocode.spring.project.repo.CartRepository;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final BookRepository bookRepository;

    @Override
    public Cart getCart(Long  userId) {
      return cartRepository.findByUserId(userId)
              .orElseThrow(() -> new CartException("Cart not found"));
    }

    public Long getQuantityItemsInCart(Long userId) {
        return cartRepository.findByUserId(userId)
                .map(cart -> cart.getItems().stream()
                        .mapToLong(CartItem::getQuantity)
                        .sum())
                .orElse(0L);
    }

    // В сервисе
    @Override
    @Transactional
    public boolean addCartItem(Long bookId, User user) {
        // 1. Находим книгу
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        // 2. Получаем корзину пользователя
        Cart cart = getCart(user.getId());

        // 3. Проверяем, есть ли уже такая книга в корзине
        Optional<CartItem> existingItem = cart.getItems().stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst();

        if (existingItem.isPresent()) {
            // Если есть — увеличиваем количество
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + 1);
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
    public boolean plusOneToCartItem(BookReq bookReq) {
        return false;
    }

    @Override
    public boolean minusOneToCartItem(BookReq bookReq) {
        return false;
    }

    @Override
    public boolean removeCartItem(BookReq bookReq) {
        return false;
    }
}

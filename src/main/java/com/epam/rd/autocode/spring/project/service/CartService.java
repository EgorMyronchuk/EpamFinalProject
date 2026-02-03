package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.request.book.BookReq;
import com.epam.rd.autocode.spring.project.dto.response.cart.CartRes;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.Cart;
import com.epam.rd.autocode.spring.project.model.User;

public interface CartService {

    public CartRes getCart(Long userId);

    boolean addCartItem(Long bookId, Long userId , int delta);

    Long getQuantityItemsInCart(Long bookId);

    public void plusOneToCartItem(Long bookId, Long userId);

    public void minusOneToCartItem(Long bookId, Long userId);

    public void removeCartItem(Long bookId, Long userId);


}

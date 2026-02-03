package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.request.book.BookReq;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.Cart;
import com.epam.rd.autocode.spring.project.model.User;

public interface CartService {

    public Cart getCart(Long userId);

    boolean addCartItem(Long bookId, User user);

    Long getQuantityItemsInCart(Long bookId);

    public boolean plusOneToCartItem(BookReq bookReq);

    public boolean minusOneToCartItem(BookReq bookReq);

    public boolean removeCartItem(BookReq bookReq);


}

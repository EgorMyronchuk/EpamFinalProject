package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.request.book.BookReq;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.Cart;

public interface CartService {

    public Cart getCart(String userEmail);

    public boolean addCartItem(BookReq bookReq);

    public boolean plusOneToCartItem(BookReq bookReq);

    public boolean minusOneToCartItem(BookReq bookReq);

    public boolean removeCartItem(BookReq bookReq);

}

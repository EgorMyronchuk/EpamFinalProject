package com.epam.rd.autocode.spring.project.dto.response.cart;

import com.epam.rd.autocode.spring.project.model.CartItem;
import com.epam.rd.autocode.spring.project.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartRes {

    private List<CartItem> items;

    private BigDecimal totalPrice;
}

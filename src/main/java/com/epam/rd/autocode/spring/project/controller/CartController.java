package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.response.user.UserRes;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long bookId, Principal principal, HttpServletRequest request) {
        Long userId = userService.getUserIdByEmail(principal.getName());
        cartService.plusOneToCartItem(bookId, userId);

        String referer = request.getHeader("Referer");

        return "redirect:" + (referer != null ? referer : "/");
    }

    @PostMapping("/plus")
    public String plusItem(@RequestParam Long bookId, Principal principal) {
        Long userId = userService.getUserIdByEmail(principal.getName());
        cartService.plusOneToCartItem(bookId, userId);
        return "redirect:/orders";
    }

    @PostMapping("/minus")
    public String minusItem(@RequestParam Long bookId, Principal principal) {
        Long userId = userService.getUserIdByEmail(principal.getName());
        cartService.minusOneToCartItem(bookId, userId);
        return "redirect:/orders";
    }

    @PostMapping("/remove")
    public String removeItem(@RequestParam Long bookId, Principal principal) {
        Long userId = userService.getUserIdByEmail(principal.getName());
        cartService.removeCartItem(bookId, userId);
        return "redirect:/orders";
    }



}

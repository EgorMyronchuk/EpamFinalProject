package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.response.cart.CartRes;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.OrderService;
import com.epam.rd.autocode.spring.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CartService cartService;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public String showCart(Model model, Principal principal) {
        Long userId = userService.getUserIdByEmail(principal.getName());

        CartRes cartRes = cartService.getCart(userId);

        model.addAttribute("cart", cartRes);

        return "orders";
    }

    @PostMapping("/create")
    public String createOrder(Principal principal, RedirectAttributes redirectAttributes) {
        Long userId = userService.getUserIdByEmail(principal.getName());
        try {
            orderService.createOrder(userId);
            redirectAttributes.addFlashAttribute("successMessage", "Замовлення успішно оформлено!");
            return "redirect:/home";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("loginError", e.getMessage());
            return "redirect:/orders";
        }
    }

    @PostMapping("/delete")
    public String cancelOrder(@RequestParam Long orderId, RedirectAttributes redirectAttributes) {
        try {
            orderService.deleteOrder(orderId);
            redirectAttributes.addFlashAttribute("successMessage", "Order cancelled successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("loginError", e.getMessage());
        }
        return "redirect:/profile";
    }
}
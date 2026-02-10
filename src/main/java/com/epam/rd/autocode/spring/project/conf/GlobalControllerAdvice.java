package com.epam.rd.autocode.spring.project.conf;

import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.utils.CurrencyConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Locale;

@ControllerAdvice(basePackages = "com.epam.rd.autocode.spring.project.controller")
@RequiredArgsConstructor

public class GlobalControllerAdvice {

    private final CartService cartService;
    private final UserRepository userRepository;
    private final ClientService clientService;

    @ModelAttribute("userBalance")
    public BigDecimal addBalanceToModel(Locale locale) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth == null || !auth.isAuthenticated() || auth instanceof AnonymousAuthenticationToken) {
                return null;
            }

            String email = auth.getName();

            boolean isClient = auth.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_USER"));

            if (isClient) {
                return clientService.getBalance(email);
            }

        } catch (Exception e) {
            System.err.println("Помилка при отримані баланса: " + e.getMessage());
        }
        return null;
    }

    @ModelAttribute("cartItemsCount")
    public Long getCartItemsCount(Principal principal) {
        if (principal == null) return 0L;

        return userRepository.findByEmail(principal.getName())
                .map(user -> cartService.getQuantityItemsInCart(user.getId()))
                .orElse(0L);
    }
}

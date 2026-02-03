package com.epam.rd.autocode.spring.project.conf;

import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.ClientService;
import lombok.RequiredArgsConstructor;
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
    public BigDecimal addBalanceToModel(Principal principal, Locale locale) {
        if (principal == null) return null;
        return clientService.getBalanceInCurrentLocale(principal.getName(), locale);
    }

    @ModelAttribute("cartItemsCount")
    public Long getCartItemsCount(Principal principal) {
        if (principal == null) return 0L;

        return userRepository.findByEmail(principal.getName())
                .map(user -> cartService.getQuantityItemsInCart(user.getId()))
                .orElse(0L);
    }
}

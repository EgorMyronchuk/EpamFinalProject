package com.epam.rd.autocode.spring.project.exception.handler;

import com.epam.rd.autocode.spring.project.dto.request.auth.SignInReq;
import com.epam.rd.autocode.spring.project.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler({
            AlreadyExistException.class,
            BookException.class,
            CartException.class,
            NotEnoughMoneyException.class,
            OrderCustomException.class,
            UserAccountDisabledException.class,
            TokenException.class
    })
    public String handleBusinessExceptions(RuntimeException ex, HttpServletRequest request, Locale locale) {

        String translatedMessage = translate(ex.getMessage(), locale);
        return buildRedirect(request, translatedMessage);
    }

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(NotFoundException ex, HttpServletRequest request, Locale locale) {
        String prefix = translate("error.not_found_prefix", locale);
        String message = prefix + ": " + ex.getMessage();

        return buildRedirect(request, message);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String handleBadCredentials(BadCredentialsException ex, Model model, Locale locale) {
        model.addAttribute("signInReq", new SignInReq());
        model.addAttribute("loginError", translate("error.bad_credentials", locale));
        return "login";
    }

    @ExceptionHandler(DisabledException.class)
    public String handleUserDisabled(DisabledException ex, Model model, Locale locale) {
        model.addAttribute("signInReq", new SignInReq());
        model.addAttribute("loginError", translate("error.account_disabled", locale));
        return "login";
    }


    private String translate(String key, Locale locale) {
        try {
            return messageSource.getMessage(key, null, locale);
        } catch (NoSuchMessageException e) {
            log.error("Message key not found: {}", key);
            return key;
        }
    }

    private String buildRedirect(HttpServletRequest request, String message) {
        String referer = request.getHeader("Referer");
        String target = (referer != null && !referer.isEmpty()) ? referer : "/home";

        target = target.replaceAll("([?&])loginError=[^&]*&?", "$1").replaceAll("[?&]$", "");

        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);
        String separator = target.contains("?") ? "&" : "?";

        return "redirect:" + target + separator + "loginError=" + encodedMessage;
    }
}
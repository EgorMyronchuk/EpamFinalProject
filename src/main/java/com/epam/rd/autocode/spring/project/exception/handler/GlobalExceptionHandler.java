package com.epam.rd.autocode.spring.project.exception.handler;

import com.epam.rd.autocode.spring.project.dto.execptionDTO.ErrorResp;
import com.epam.rd.autocode.spring.project.dto.request.auth.SignInReq;
import com.epam.rd.autocode.spring.project.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({
            AlreadyExistException.class,
            BookException.class,
            CartException.class,
            NotEnoughMoneyException.class,
            OrderCustomException.class
    })
    public String handleBusinessExceptions(RuntimeException ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        log.error("Business error: {}", ex.getMessage());

        redirectAttributes.addFlashAttribute("loginError", ex.getMessage());

        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/home");
    }

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(NotFoundException ex, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("loginError", "Ресурс не знайдено: " + ex.getMessage());
        String referer = request.getHeader("Referer");
        return "redirect:" + (referer != null ? referer : "/home");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String handleBadCredentials(BadCredentialsException ex, Model model) {
        model.addAttribute("signInReq", new SignInReq());

        model.addAttribute("loginError", "Невірна пошта або пароль");
        return "login";
    }

    @ExceptionHandler(DisabledException.class)
    public String handleBaUserAccountDisabledException(DisabledException ex, Model model) {
        model.addAttribute("signInReq", new SignInReq());

        model.addAttribute("loginError", "Аккаунт заблоковано або він був видалений");
        return "login";
    }


}


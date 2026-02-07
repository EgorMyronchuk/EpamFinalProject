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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({
            AlreadyExistException.class,
            NotEnoughMoneyException.class,
            OrderCustomException.class
    })
    public String handleBusinessExceptions(RuntimeException ex, HttpServletRequest request) {
        System.out.println("1 - Handler Triggered for Stateless Redirect");

        String errorMessage = URLEncoder.encode(ex.getMessage(), StandardCharsets.UTF_8);
        String referer = request.getHeader("Referer");

        if (referer == null || referer.isEmpty()) {
            return "redirect:/home?loginError=" + errorMessage;
        }

        // Проверяем, есть ли уже в URL параметры (знак вопроса)
        // Чтобы не получилось ...?page=1?loginError=...
        String separator = referer.contains("?") ? "&" : "?";

        // Если ошибка уже была в URL, она может задублироваться.
        // В идеале её стоит вырезать через regex, но для начала хватит и этого:
        return "redirect:" + referer + separator + "loginError=" + errorMessage;
    }

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFound(NotFoundException ex, HttpServletRequest request) {
        System.out.println("2 - NotFound Handler Triggered");

        String message = "Ресурс не знайдено: " + ex.getMessage();
        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);

        String referer = request.getHeader("Referer");

        String target = (referer != null && !referer.isEmpty()) ? referer : "/home";

        String separator = target.contains("?") ? "&" : "?";

        return "redirect:" + target + separator + "loginError=" + encodedMessage;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String handleBadCredentials(BadCredentialsException ex, Model model) {
        model.addAttribute("signInReq", new SignInReq());
        System.out.println("3");
        model.addAttribute("loginError", "Невірна пошта або пароль");
        return "login";
    }

    @ExceptionHandler(DisabledException.class)
    public String handleBaUserAccountDisabledException(DisabledException ex, Model model) {
        model.addAttribute("signInReq", new SignInReq());
        System.out.println("4");
        model.addAttribute("loginError", "Аккаунт заблоковано або він був видалений");
        return "login";
    }


}


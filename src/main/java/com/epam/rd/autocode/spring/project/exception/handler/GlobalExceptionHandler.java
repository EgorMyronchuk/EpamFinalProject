package com.epam.rd.autocode.spring.project.exception.handler;

import com.epam.rd.autocode.spring.project.dto.execptionDTO.ErrorResp;
import com.epam.rd.autocode.spring.project.dto.request.auth.SignInReq;
import com.epam.rd.autocode.spring.project.exception.ExceptionConstants;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.exception.UserAccountDisabledException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResp> handleNotFoundException(NotFoundException ex) {
        log.debug("Route not found: {}", ex.getMessage());
        return new ResponseEntity<>(new ErrorResp(ExceptionConstants.NOT_FOUND), HttpStatus.NOT_FOUND);
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


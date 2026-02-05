package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.jwtDTO.JwtAuthenticationResponse;
import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.dto.request.auth.SignInReq;
import com.epam.rd.autocode.spring.project.service.authService.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("createUserReq", new CreateUserReq());
        return "register";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("createUserReq") CreateUserReq createUserReq,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        authService.signUpForUser(createUserReq);
        return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {

        model.addAttribute("signInReq", new SignInReq());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("signInReq") SignInReq request,
                        BindingResult bindingResult,
                        HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        JwtAuthenticationResponse jwtResponse = authService.signIn(request);

        Cookie jwtCookie = new Cookie("jwt", jwtResponse.getToken());
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(24 * 60 * 60);
        response.addCookie(jwtCookie);

        return "redirect:/home";
    }
}


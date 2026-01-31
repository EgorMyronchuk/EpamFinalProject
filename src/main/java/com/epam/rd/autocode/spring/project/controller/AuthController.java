package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.jwtDTO.JwtAuthenticationResponse;
import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.dto.request.auth.SignInReq;
import com.epam.rd.autocode.spring.project.service.authService.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @GetMapping()
    public String showRegistrationForm(Model model) {
        model.addAttribute("createUserReq", new CreateUserReq());
        return "register";
    }


    @PostMapping()
    public String registration(@ModelAttribute CreateUserReq createUserReq){
        authService.signUpForUser(createUserReq);

         return "redirect:/auth/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("createUserReq", new CreateUserReq());
        return "login";
    }


    @PostMapping("/login")
    public String login(@ModelAttribute SignInReq request, HttpServletResponse response) {

        JwtAuthenticationResponse jwtResponse = authService.signIn(request);

        Cookie jwtCookie = new Cookie("jwt", jwtResponse.getToken());
        jwtCookie.setHttpOnly(true); // важно, чтобы JS не читал
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(24 * 60 * 60); // 1 день
        response.addCookie(jwtCookie);

        return "/home";
    }

    //add method to refresh password
}

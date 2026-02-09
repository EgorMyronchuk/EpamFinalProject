package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.jwtDTO.JwtAuthenticationResponse;
import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.dto.request.auth.SignInReq;
import com.epam.rd.autocode.spring.project.exception.TokenException;
import com.epam.rd.autocode.spring.project.model.RefreshToken;
import com.epam.rd.autocode.spring.project.model.UserPrincipal;
import com.epam.rd.autocode.spring.project.repo.RefreshTokenRepository;
import com.epam.rd.autocode.spring.project.service.authService.AuthenticationService;
import com.epam.rd.autocode.spring.project.service.authService.CustomUserDetailsService;
import com.epam.rd.autocode.spring.project.service.authService.JwtService;
import com.epam.rd.autocode.spring.project.service.authService.RefreshTokenService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;

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

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(request.getEmail());

        Cookie jwtCookie = new Cookie("jwt", jwtResponse.getToken());
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(15 * 60);
        response.addCookie(jwtCookie);

        Cookie refreshCookie = new Cookie("refresh_token", refreshToken.getToken());
        refreshCookie.setHttpOnly(true);
        refreshCookie.setPath("/");
        refreshCookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(refreshCookie);

        String email = jwtService.extractEmailName(jwtResponse.getToken());
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            return "redirect:/secret/admin";
        }

        return "redirect:/home";
    }

    @PostMapping("/refresh")
    public String refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String refreshTokenString = getCookieValue(request, "refresh_token");

        return refreshTokenRepository.findByToken(refreshTokenString)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String newAccessToken = jwtService.generateToken(new UserPrincipal(user));
                    Cookie accessCookie = new Cookie("jwt", newAccessToken);
                    accessCookie.setHttpOnly(true);
                    accessCookie.setPath("/");
                    accessCookie.setMaxAge(15 * 60);
                    response.addCookie(accessCookie);

                    return "redirect:/home";
                })
                .orElse("redirect:/auth/login");
    }

    private String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() == null) return null;
        for (Cookie cookie : request.getCookies()) {
            if (name.equals(cookie.getName())) {
                return cookie.getValue();
            }
        }
        return null;
    }
}


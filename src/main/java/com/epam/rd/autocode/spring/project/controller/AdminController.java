package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.service.authService.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Controller
@RequestMapping("/secret")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final AuthenticationService authenticationService;
    private final MessageSource messageSource;

    @GetMapping("/admin")
    public String showAdmin(Model model) {
        model.addAttribute("createUserReq", new CreateUserReq());
        return "admin-page";
    }

    @PostMapping("/admin")
    public String addEmployee(@Valid @ModelAttribute("createUserReq") CreateUserReq createUserReq,
                              BindingResult bindingResult,
                              Locale locale) {
        if (bindingResult.hasErrors()) {
            return "admin-page";
        }

        authenticationService.signUpForEmployee(createUserReq);

        String msg = messageSource.getMessage("success.employee_created", null, locale);
        return "redirect:/secret/admin?successMessage=" + URLEncoder.encode(msg, StandardCharsets.UTF_8);
    }

}

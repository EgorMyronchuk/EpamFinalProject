package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.service.authService.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/secret")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN')")
public class AdminController {

    private final AuthenticationService authenticationService;

    @GetMapping("/admin")
    public String showAdmin(Model model) {
        model.addAttribute("createUserReq", new CreateUserReq());
        return "admin-page";
    }

    @PostMapping("/admin")
    public String addEmployee(@Valid @ModelAttribute("createUserReq") CreateUserReq createUserReq,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        authenticationService.signUpForEmployee(createUserReq);
        return "redirect:/secret/admin";
    }

}

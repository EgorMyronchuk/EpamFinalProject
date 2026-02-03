package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelReq;
import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelRes;
import com.epam.rd.autocode.spring.project.dto.response.order.OrderRes;
import com.epam.rd.autocode.spring.project.service.OrderService;
import com.epam.rd.autocode.spring.project.service.ProfileService;
import com.epam.rd.autocode.spring.project.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;
    private final OrderService orderService;

    @GetMapping
    public String showProfile(Principal principal, Model model) {
        String email = principal.getName();
        ClientBusModelRes profile = profileService.getProfileByEmail(email);

        List<OrderRes> orders = orderService.getOrdersByClient(email);

        model.addAttribute("profile", profile);
        model.addAttribute("orders", orders);
        return "profile";
    }

    @PostMapping("/update")
    public String updateProfile(@Valid @ModelAttribute("profile") ClientBusModelReq dto,
                                BindingResult bindingResult,
                                Principal principal,
                                RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        profileService.updateProfileByEmail(principal.getName(), dto);
        redirectAttributes.addFlashAttribute("successMessage", "Profile updated successfully!");
        return "redirect:/profile";
    }

    @PostMapping("/delete")
    public String deleteAccount(Principal principal) {
        userService.deleteUserByEmail(principal.getName());
        return "redirect:/logout";
    }

    @GetMapping("/update")
    public String handleGetUpdate() {
        return "redirect:/profile";
    }


}

package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelReq;
import com.epam.rd.autocode.spring.project.dto.request.employee.EmployeeBusModelReq;
import com.epam.rd.autocode.spring.project.dto.response.client.ClientBusModelRes;
import com.epam.rd.autocode.spring.project.dto.response.employee.EmployeeBusModelRes;
import com.epam.rd.autocode.spring.project.dto.response.order.OrderRes;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.service.OrderService;
import com.epam.rd.autocode.spring.project.service.ProfileService;
import com.epam.rd.autocode.spring.project.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;
    private final ClientService clientService;
    private final OrderService orderService;
    private final MessageSource messageSource;

    @GetMapping("")
    public String showProfile(Authentication authentication, Model model) {
        if (authentication == null) return "redirect:/auth/login";

        String email = authentication.getName();

        List<String> authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();


        String displayRole = authorities.get(0).replace("ROLE_", "");
        model.addAttribute("userRole", displayRole);

        if (authorities.contains("ROLE_EMPLOYEE") || authorities.contains("ROLE_ADMIN")) {
            EmployeeBusModelRes employeeProfile = profileService.getProfileForEmployeeByEmail(email);
            model.addAttribute("profile", employeeProfile);
            return "profile-staff";
        }

        ClientBusModelRes clientProfile = profileService.getProfileByEmail(email);
        model.addAttribute("profile", clientProfile);
        model.addAttribute("orders", orderService.getOrdersByClient(email));

        return "profile";
    }

    @PostMapping("/update-staff")
    public String updateStaffProfile(@ModelAttribute("profile") EmployeeBusModelReq updateDto,
                                     Authentication authentication,
                                     Locale locale) {
        profileService.updateEmployeeByEmail(authentication.getName(), updateDto);

        String msg = messageSource.getMessage("success.profile_updated", null, locale);
        return "redirect:/profile?successMessage=" + URLEncoder.encode(msg, StandardCharsets.UTF_8);
    }

    @PostMapping("/update")
    public String updateProfile(@Valid @ModelAttribute("profile") ClientBusModelReq dto,
                                BindingResult bindingResult,
                                Principal principal,
                                Locale locale) {
        if (bindingResult.hasErrors()) {
            return "profile";
        }
        profileService.updateProfileByEmail(principal.getName(), dto);

        String msg = messageSource.getMessage("success.profile_updated", null, locale);
        return "redirect:/profile?successMessage=" + URLEncoder.encode(msg, StandardCharsets.UTF_8);
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

    @PostMapping("/deposit")
    public String depositMoney(@RequestParam Long amount,
                               Principal principal,
                               HttpServletRequest request,
                               Locale locale) {
        String email = principal.getName();
        BigDecimal newTotal = clientService.getBalance(email).add(new BigDecimal(amount));
        clientService.changeBalance(email, newTotal);

        String msg = messageSource.getMessage("success.balance_added", new Object[]{amount}, locale);

        String referer = request.getHeader("Referer");
        String target = (referer != null) ? referer : "/profile";

        target = target.replaceAll("([?&])(successMessage|loginError)=[^&]*&?", "$1").replaceAll("[?&]$", "");
        String separator = target.contains("?") ? "&" : "?";

        return "redirect:" + target + separator + "successMessage=" + URLEncoder.encode(msg, StandardCharsets.UTF_8);
    }
}

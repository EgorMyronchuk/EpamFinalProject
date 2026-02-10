package com.epam.rd.autocode.spring.project.controller;

import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelReq;
import com.epam.rd.autocode.spring.project.dto.request.employee.EmployeeBusModelReq;
import com.epam.rd.autocode.spring.project.dto.response.client.ClientBusModelRes;
import com.epam.rd.autocode.spring.project.dto.response.employee.EmployeeBusModelRes;
import com.epam.rd.autocode.spring.project.repo.RefreshTokenRepository;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.service.OrderService;
import com.epam.rd.autocode.spring.project.service.ProfileService;
import com.epam.rd.autocode.spring.project.service.UserService;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.authService.JwtService;
import com.epam.rd.autocode.spring.project.service.CartService;
import com.epam.rd.autocode.spring.project.service.authService.RefreshTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfileController.class)
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;
    @MockBean
    private UserService userService;
    @MockBean
    private ClientService clientService;
    @MockBean
    private OrderService orderService;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private RefreshTokenService refreshTokenService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private CartService cartService;
    @MockBean
    private RefreshTokenRepository refreshTokenRepository;

    @Test
    @WithMockUser(username = "client@test.com", roles = "CLIENT")
    void showProfile_AsClient_ShouldReturnClientProfile() throws Exception {
        ClientBusModelRes clientRes = new ClientBusModelRes();
        when(profileService.getProfileByEmail("client@test.com")).thenReturn(clientRes);
        when(orderService.getOrdersByClient("client@test.com")).thenReturn(List.of());

        mockMvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile"))
                .andExpect(model().attribute("userRole", "CLIENT"))
                .andExpect(model().attributeExists("profile", "orders"));
    }

    @Test
    @WithMockUser(username = "admin@test.com", roles = "ADMIN")
    void showProfile_AsAdmin_ShouldReturnStaffProfile() throws Exception {
        EmployeeBusModelRes employeeRes = new EmployeeBusModelRes();
        when(profileService.getProfileForEmployeeByEmail("admin@test.com")).thenReturn(employeeRes);

        mockMvc.perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("profile-staff"))
                .andExpect(model().attribute("userRole", "ADMIN"))
                .andExpect(model().attribute("profile", employeeRes));
    }

    @Test
    @WithMockUser(username = "client@test.com")
    void updateProfile_Success_ShouldRedirect() throws Exception {
        ClientBusModelReq validDto = new ClientBusModelReq();
        validDto.setEmail("client@test.com");
        validDto.setName("John Doe");
        validDto.setPhoneNumber("0950123456");
        validDto.setDeliveryAddress("Street 1");

        mockMvc.perform(post("/profile/update")
                        .with(csrf())
                        .flashAttr("profile", validDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/profile?successMessage=*"));

        verify(profileService).updateProfileByEmail(eq("client@test.com"), any(ClientBusModelReq.class));
    }

    @Test
    @WithMockUser(username = "client@test.com")
    void depositMoney_ShouldUpdateBalanceAndRedirect() throws Exception {
        String email = "client@test.com";
        when(clientService.getBalance(email)).thenReturn(new BigDecimal("100"));

        mockMvc.perform(post("/profile/deposit")
                        .param("amount", "50")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("/profile?successMessage=*"));

        verify(clientService).changeBalance(eq(email), eq(new BigDecimal("150")));
    }

    @Test
    @WithMockUser(username = "client@test.com")
    void deleteAccount_ShouldRedirectToLogout() throws Exception {
        mockMvc.perform(post("/profile/delete")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/logout"));

        verify(userService).deleteUserByEmail("client@test.com");
    }
}
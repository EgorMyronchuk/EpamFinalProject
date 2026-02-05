package com.epam.rd.autocode.spring.project.conf;

import com.epam.rd.autocode.spring.project.model.Cart;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.model.enums.Role;
import com.epam.rd.autocode.spring.project.repo.CartRepository;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Value("${ADMIN_EMAIL}")
    private String adminEmail;

    @Value("${ADMIN_PASS}")
    private String adminPass;

    @Value("${EMPLOYEE_EMAIL}")
    private String employeeEmail;

    @Value("${EMPLOYEE_PASS}")
    private String employeePass;

    @Value("${USER_EMAIL}")
    private String userEmail;

    @Value("${USER_PASS}")
    private String userPass;

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, CartRepository cartRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByRole(Role.ADMIN).isEmpty()) {
                User admin = new User();
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode(adminPass));
                admin.setRole(Role.ADMIN);
                userRepository.save(admin);
            }
            if (userRepository.findByEmail("employee@gmail.com").isEmpty()) {
                User employee = new User();
                employee.setEmail(employeeEmail);
                employee.setPassword(passwordEncoder.encode(employeePass));
                employee.setRole(Role.EMPLOYEE);
                employee.setActive(true);

                Employee employeeProfile = new Employee(employee);
                employee.setEmployeeProfile(employeeProfile);

                userRepository.save(employee);

                Cart cart = new Cart(employee);
                cartRepository.save(cart);
            }
            if (userRepository.findByEmail("egormyronchuk@gmail.com").isEmpty()) {
                User user = new User();
                user.setEmail(userEmail);
                user.setPassword(passwordEncoder.encode(userPass));
                user.setName("Egor");
                user.setRole(Role.USER);
                Client clientProfile = new Client(user);
                clientProfile.setBalance(new BigDecimal("100.00"));
                user.setClientProfile(clientProfile);
                userRepository.save(user);

                Cart cart = new Cart(user);
                cartRepository.save(cart);
            }

        };
    }

}

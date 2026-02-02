package com.epam.rd.autocode.spring.project.conf;

import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.model.enums.Role;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByRole(Role.ROLE_ADMIN).isEmpty()) {
                User admin = new User();
                admin.setEmail("adminadmin@gmail.com");
                admin.setPassword(passwordEncoder.encode("Admin_pass"));
                admin.setRole(Role.ROLE_ADMIN);
                userRepository.save(admin);
            }
            if (userRepository.findByEmail("employee@gmail.com").isEmpty()) {
                User employee = new User();
                employee.setEmail("employee@gmail.com");
                employee.setPassword(passwordEncoder.encode("Employee"));
                employee.setRole(Role.ROLE_EMPLOYEE);
                userRepository.save(employee);
            }
            if (userRepository.findByEmail("egormyronchuk@gmail.com").isEmpty()) {
                User user = new User();
                user.setEmail("egormyronchuk@gmail.com");
                user.setPassword(passwordEncoder.encode("Egor2004"));
                user.setName("Egor");
                user.setRole(Role.ROLE_USER);

                Client clientProfile = new Client(user);
                clientProfile.setBalance(new BigDecimal("100.00"));
                user.setClientProfile(clientProfile);

                userRepository.save(user);
            }

        };
    }

}

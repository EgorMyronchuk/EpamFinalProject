package com.epam.rd.autocode.spring.project.service.authService;

import com.epam.rd.autocode.spring.project.dto.jwtDTO.JwtAuthenticationResponse;
import com.epam.rd.autocode.spring.project.dto.mapper.UserMapper;
import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.dto.request.auth.SignInReq;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.exception.ExceptionConstants;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.exception.UserAccountDisabledException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.UserPrincipal;
import com.epam.rd.autocode.spring.project.model.enums.Role;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.epam.rd.autocode.spring.project.model.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public JwtAuthenticationResponse signUpForUser(CreateUserReq request) {

        if (userRepository.existsByEmail(request.getEmail())){
            throw new AlreadyExistException(ExceptionConstants.EMAIL_EXISTS);
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);
        user.setActive(true);

        Client client = new Client(user);
        user.setClientProfile(client);

        userRepository.save(user);

        UserDetails userDetails = new UserPrincipal(user);
        String jwt = jwtService.generateToken(userDetails);

        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInReq request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();

        if (!principal.getUser().isActive()) {
            throw new UserAccountDisabledException("Account is disabled");
        }

        String jwt = jwtService.generateToken(principal);
        return new JwtAuthenticationResponse(jwt);
    }

}
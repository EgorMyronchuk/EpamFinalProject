package com.epam.rd.autocode.spring.project.service.authService;

import com.epam.rd.autocode.spring.project.dto.jwtDTO.JwtAuthenticationResponse;
import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.dto.request.auth.SignInReq;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.model.UserPrincipal;
import com.epam.rd.autocode.spring.project.repo.CartRepository;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private CartRepository cartRepository;
    @Mock private JwtService jwtService;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @Test
    void signUpForUser_Success() {
        CreateUserReq req = new CreateUserReq();
        req.setEmail("new@user.com");
        req.setPassword("Password123");
        req.setName("Ivan");

        when(userRepository.existsByEmail(req.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(req.getPassword())).thenReturn("encoded_pass");
        when(jwtService.generateToken(any())).thenReturn("mocked_jwt");

        JwtAuthenticationResponse response = authenticationService.signUpForUser(req);

        assertNotNull(response);
        assertEquals("mocked_jwt", response.getToken());
        verify(userRepository).save(any(User.class));
        verify(cartRepository).save(any());
    }

    @Test
    void signUpForUser_ShouldThrowException_WhenEmailExists() {
        CreateUserReq req = new CreateUserReq();
        req.setEmail("exists@user.com");
        when(userRepository.existsByEmail(req.getEmail())).thenReturn(true);

        assertThrows(AlreadyExistException.class, () -> authenticationService.signUpForUser(req));
        verify(userRepository, never()).save(any());
    }

    @Test
    void signIn_Success() {
        SignInReq req = new SignInReq("test@test.com", "Password123");

        User user = new User();
        user.setEmail(req.getEmail());
        UserPrincipal principal = new UserPrincipal(user);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(principal);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtService.generateToken(principal)).thenReturn("signed_jwt");

        JwtAuthenticationResponse response = authenticationService.signIn(req);

        assertNotNull(response);
        assertEquals("signed_jwt", response.getToken());
        verify(authenticationManager).authenticate(any());
    }
}
package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.mapper.UserMapper;
import com.epam.rd.autocode.spring.project.dto.response.user.UserStatusRes;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private final String email = "user@example.com";

    @Test
    void getAllUsersWithStaff_ShouldReturnPageOfUserStatusRes() {
        Pageable pageable = PageRequest.of(0, 10);
        User user = new User();
        UserStatusRes statusRes = new UserStatusRes(email, "Name", true);
        Page<User> userPage = new PageImpl<>(List.of(user));

        when(userRepository.findAll(pageable)).thenReturn(userPage);
        when(userMapper.toStatusDto(user)).thenReturn(statusRes);

        Page<UserStatusRes> result = userService.getAllUsersWithStaff(pageable);

        assertEquals(1, result.getContent().size());
        assertEquals(email, result.getContent().get(0).getEmail());
        verify(userRepository).findAll(pageable);
    }

    @Test
    void getUserIdByEmail_ShouldReturnId_WhenUserExists() {
        User user = new User();
        user.setId(100L);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Long id = userService.getUserIdByEmail(email);

        assertEquals(100L, id);
    }

    @Test
    void getUserIdByEmail_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.getUserIdByEmail(email));
    }

    @Test
    void deleteUserByEmail_ShouldSetDeactivationFields() {
        User user = new User();
        user.setActive(true);
        user.setDeletedAt(null);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        userService.deleteUserByEmail(email);

        assertFalse(user.isActive());
        assertNotNull(user.getDeletedAt());
        verify(userRepository).save(user);
    }

    @Test
    void restore_ShouldSetActiveFields() {
        User user = new User();
        user.setActive(false);
        user.setDeletedAt(java.time.LocalDateTime.now());
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        userService.restore(email);

        assertTrue(user.isActive());
        assertNull(user.getDeletedAt());
        verify(userRepository).save(user);
    }

    @Test
    void deleteUserByEmail_ShouldThrowException_WhenUserNotFound() {
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.deleteUserByEmail(email));
        verify(userRepository, never()).save(any());
    }
}
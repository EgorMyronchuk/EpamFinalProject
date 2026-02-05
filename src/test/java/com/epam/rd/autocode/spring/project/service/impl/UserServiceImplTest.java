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

        // When
        Page<UserStatusRes> result = userService.getAllUsersWithStaff(pageable);

        // Then
        assertEquals(1, result.getContent().size());
        assertEquals(email, result.getContent().get(0).getEmail());
        verify(userRepository).findAll(pageable);
    }

    @Test
    void getUserIdByEmail_ShouldReturnId_WhenUserExists() {
        // Given
        User user = new User();
        user.setId(100L);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // When
        Long id = userService.getUserIdByEmail(email);

        // Then
        assertEquals(100L, id);
    }

    @Test
    void getUserIdByEmail_ShouldThrowException_WhenUserNotFound() {
        // Given
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> userService.getUserIdByEmail(email));
    }

    @Test
    void deleteUserByEmail_ShouldSetDeactivationFields() {
        // Given
        User user = new User();
        user.setActive(true);
        user.setDeletedAt(null);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // When
        userService.deleteUserByEmail(email);

        // Then
        assertFalse(user.isActive());
        assertNotNull(user.getDeletedAt());
        verify(userRepository).save(user);
    }

    @Test
    void restore_ShouldSetActiveFields() {
        // Given
        User user = new User();
        user.setActive(false);
        user.setDeletedAt(java.time.LocalDateTime.now());
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // When
        userService.restore(email);

        // Then
        assertTrue(user.isActive());
        assertNull(user.getDeletedAt());
        verify(userRepository).save(user);
    }

    @Test
    void deleteUserByEmail_ShouldThrowException_WhenUserNotFound() {
        // Given
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NotFoundException.class, () -> userService.deleteUserByEmail(email));
        verify(userRepository, never()).save(any());
    }
}
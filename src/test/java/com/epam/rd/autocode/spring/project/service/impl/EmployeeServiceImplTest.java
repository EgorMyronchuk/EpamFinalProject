package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private UserService userService;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private final String testEmail = "employee@example.com";

    @Test
    void blockUserByEmail_ShouldInvokeUserServiceDelete() {

        employeeService.blockUserByEmail(testEmail);

        verify(userService, times(1)).deleteUserByEmail(testEmail);
    }

    @Test
    void unBlockUserByEmail_ShouldInvokeUserServiceRestore() {

        employeeService.unBlockUserByEmail(testEmail);

        verify(userService, times(1)).restore(testEmail);
    }
}
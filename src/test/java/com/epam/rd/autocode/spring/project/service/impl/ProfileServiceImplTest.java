package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.mapper.ProfileMapper;
import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelReq;
import com.epam.rd.autocode.spring.project.dto.request.employee.EmployeeBusModelReq;
import com.epam.rd.autocode.spring.project.dto.response.client.ClientBusModelRes;
import com.epam.rd.autocode.spring.project.dto.response.employee.EmployeeBusModelRes;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ProfileMapper profileMapper;

    @InjectMocks
    private ProfileServiceImpl profileService;

    private final String email = "test@example.com";

    @Test
    void getProfileByEmail_ShouldReturnProfile_WhenClientExists() {
        Client client = new Client();
        ClientBusModelRes expectedRes = new ClientBusModelRes();

        when(clientRepository.findByUserEmail(email)).thenReturn(Optional.of(client));
        when(profileMapper.toBusModel(client)).thenReturn(expectedRes);

        ClientBusModelRes actualRes = profileService.getProfileByEmail(email);

        assertNotNull(actualRes);
        verify(clientRepository).findByUserEmail(email);
    }

    @Test
    void getProfileByEmail_ShouldThrowException_WhenClientNotFound() {
        when(clientRepository.findByUserEmail(email)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> profileService.getProfileByEmail(email));
    }

    @Test
    void getProfileForEmployeeByEmail_ShouldReturnProfile() {
        Employee employee = new Employee();
        EmployeeBusModelRes expectedRes = new EmployeeBusModelRes();

        when(employeeRepository.findByUserEmail(email)).thenReturn(Optional.of(employee));
        when(profileMapper.toBusModelForEmployee(employee)).thenReturn(expectedRes);

        EmployeeBusModelRes actualRes = profileService.getProfileForEmployeeByEmail(email);

        assertNotNull(actualRes);
        verify(employeeRepository).findByUserEmail(email);
    }

    @Test
    void updateProfileByEmail_ShouldUpdateAndReturnRes() {
        User user = new User();
        user.setName("Old Name");
        Client client = new Client();
        client.setUser(user);

        ClientBusModelReq req = new ClientBusModelReq(email, "New Name", "+380501234567", "New Address");
        ClientBusModelRes res = new ClientBusModelRes(email, "New Name", "+380501234567", "New Address");

        when(clientRepository.findByUserEmail(email)).thenReturn(Optional.of(client));
        when(profileMapper.toBusModel(any(Client.class))).thenReturn(res);

        ClientBusModelRes result = profileService.updateProfileByEmail(email, req);

        assertEquals("New Name", client.getUser().getName());
        assertEquals("New Address", client.getDeliveryAddress());
        verify(clientRepository).save(client);
        assertNotNull(result);
    }

    @Test
    void updateEmployeeByEmail_ShouldUpdateAndReturnRes() {
        User user = new User();
        user.setName("Old Name");
        Employee employee = new Employee();
        employee.setUser(user);

        LocalDate newBirthDate = LocalDate.of(1990, 1, 1);
        EmployeeBusModelReq req = new EmployeeBusModelReq(email, "New Name", "+380501112233", newBirthDate);
        EmployeeBusModelRes res = new EmployeeBusModelRes();

        when(employeeRepository.findByUserEmail(email)).thenReturn(Optional.of(employee));
        when(profileMapper.toBusModelForEmployee(any(Employee.class))).thenReturn(res);

        EmployeeBusModelRes result = profileService.updateEmployeeByEmail(email, req);

        assertEquals("New Name", employee.getUser().getName());
        assertEquals(newBirthDate, employee.getBirthDate());
        verify(employeeRepository).save(employee);
    }
}
package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.mapper.ProfileMapper;
import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelReq;
import com.epam.rd.autocode.spring.project.dto.request.employee.EmployeeBusModelReq;
import com.epam.rd.autocode.spring.project.dto.response.client.ClientBusModelRes;
import com.epam.rd.autocode.spring.project.dto.response.employee.EmployeeBusModelRes;
import com.epam.rd.autocode.spring.project.exception.ExceptionConstants;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.service.ProfileService;
import com.epam.rd.autocode.spring.project.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ClientBusModelRes getProfileByEmail(String email) {
        Client client = clientRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));

        return profileMapper.toBusModel(client);
    }

    @Override
    public EmployeeBusModelRes getProfileForEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));

        return profileMapper.toBusModelForEmployee(employee);
    }

    @Override
    @Transactional
    public ClientBusModelRes updateProfileByEmail(String email, ClientBusModelReq updateDto) {
        Client client = clientRepository.findByUserEmail(email).orElseThrow();

        client.setPhoneNumber(updateDto.getPhoneNumber());
        client.setDeliveryAddress(updateDto.getDeliveryAddress());

        client.getUser().setName(updateDto.getName());

        clientRepository.save(client);

        return profileMapper.toBusModel(client);
    }

    @Override
    @Transactional
    public EmployeeBusModelRes updateEmployeeByEmail(String email, EmployeeBusModelReq updateDto) {
        Employee employee = employeeRepository.findByUserEmail(email).orElseThrow();

        employee.setPhone(updateDto.getPhone());
        employee.setBirthDate(updateDto.getBirthDate());

        employee.getUser().setName(updateDto.getName());

        employeeRepository.save(employee);

        return profileMapper.toBusModelForEmployee(employee);
    }

}

package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.mapper.EmployeeMapper;
import com.epam.rd.autocode.spring.project.dto.request.employee.EmployeeReq;
import com.epam.rd.autocode.spring.project.dto.response.employee.EmployeeRes;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.exception.ExceptionConstants;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.repo.EmployeeRepository;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.EmployeeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserRepository userRepository;

    @Override
    public EmployeeRes addEmployee(EmployeeReq employee) {
        Employee employeeEntity = employeeMapper.toEntity(employee);
        employeeRepository.save(employeeEntity);
        return employeeMapper.toDto(employeeEntity);
    }

    @Override
    public List<EmployeeRes> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .toList();
    }

    @Override
    public EmployeeRes getEmployeeByEmail(String email) {
        Optional<Employee> employee = employeeRepository.findByUserEmail(email);
        if (employee.isPresent()) {
            return employeeMapper.toDto(employee.get());
        }
        throw new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND);
    }

    @Transactional
    public EmployeeRes updateEmployeeByEmail(String email, EmployeeReq req) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (req.getEmail() != null && !req.getEmail().equals(user.getEmail())) {
            if (userRepository.existsByEmail(req.getEmail())) {
                throw new AlreadyExistException(ExceptionConstants.EMAIL_EXISTS);
            }
        }

        Employee employee = user.getEmployeeProfile();

        employeeMapper.updateUserFromDto(req, user);
        employeeMapper.updateEmployeeFromDto(req, employee);

        userRepository.save(user);

        return employeeMapper.toDto(employee);
    }

    @Override
    public void deleteEmployeeByEmail(String email) {
        Employee client = employeeRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));

        employeeRepository.delete(client);
    }

}

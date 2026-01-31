package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.request.employee.EmployeeReq;
import com.epam.rd.autocode.spring.project.dto.response.employee.EmployeeRes;

import java.util.List;

public interface EmployeeService {

    List<EmployeeRes> getAllEmployees();

    EmployeeRes getEmployeeByEmail(String email);

    EmployeeRes updateEmployeeByEmail(String email, EmployeeReq employee);

    void deleteEmployeeByEmail(String email);

    EmployeeRes addEmployee(EmployeeReq employee);
}

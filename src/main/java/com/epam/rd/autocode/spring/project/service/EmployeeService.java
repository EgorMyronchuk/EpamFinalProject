package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.request.employee.EmployeeReq;
import com.epam.rd.autocode.spring.project.dto.response.employee.EmployeeRes;

import java.util.List;

public interface EmployeeService {

    void blockUserByEmail(String email);

    void unBlockUserByEmail(String email);
}

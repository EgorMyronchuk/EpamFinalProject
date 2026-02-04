package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelReq;
import com.epam.rd.autocode.spring.project.dto.request.employee.EmployeeBusModelReq;
import com.epam.rd.autocode.spring.project.dto.response.client.ClientBusModelRes;
import com.epam.rd.autocode.spring.project.dto.response.employee.EmployeeBusModelRes;

public interface ProfileService {

    ClientBusModelRes getProfileByEmail (String email);

    ClientBusModelRes updateProfileByEmail(String email, ClientBusModelReq updateDto);

    EmployeeBusModelRes getProfileForEmployeeByEmail(String email);

    EmployeeBusModelRes updateEmployeeByEmail(String email, EmployeeBusModelReq updateDto);
}

package com.epam.rd.autocode.spring.project.dto.mapper;

import com.epam.rd.autocode.spring.project.dto.request.employee.EmployeeReq;
import com.epam.rd.autocode.spring.project.dto.response.employee.EmployeeRes;
import com.epam.rd.autocode.spring.project.model.Employee;
import com.epam.rd.autocode.spring.project.model.User;

import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

    EmployeeRes toDto(Employee employee);

    Employee toEntity(EmployeeReq employee);

    void updateUserFromDto(EmployeeReq req, @MappingTarget User user);

    void updateEmployeeFromDto(EmployeeReq req, @MappingTarget Employee employee);
}

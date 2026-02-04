package com.epam.rd.autocode.spring.project.dto.mapper;

import com.epam.rd.autocode.spring.project.dto.response.client.ClientBusModelRes;
import com.epam.rd.autocode.spring.project.dto.response.employee.EmployeeBusModelRes;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.name", target = "name")
    ClientBusModelRes toBusModel(Client client);

    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.name", target = "name")
    EmployeeBusModelRes toBusModelForEmployee(Employee client);



}

package com.epam.rd.autocode.spring.project.dto.mapper;

import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelReq;
import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelRes;
import com.epam.rd.autocode.spring.project.dto.response.client.ClientRes;
import com.epam.rd.autocode.spring.project.dto.response.user.UserRes;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.name", target = "name")
    ClientBusModelRes toBusModel(Client client);
}

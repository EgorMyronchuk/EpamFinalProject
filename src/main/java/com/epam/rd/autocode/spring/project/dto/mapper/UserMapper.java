package com.epam.rd.autocode.spring.project.dto.mapper;

import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.dto.request.auth.SignInReq;
import com.epam.rd.autocode.spring.project.dto.response.user.UserRes;
import com.epam.rd.autocode.spring.project.dto.response.user.UserStatusRes;
import com.epam.rd.autocode.spring.project.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toEntity(CreateUserReq dto);

    User toEntity(SignInReq dto);

    UserRes toDto(User entity);

    UserStatusRes toStatusDto(User entity);
}

package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.dto.request.user.UserReq;
import com.epam.rd.autocode.spring.project.dto.response.user.UserRes;

import java.util.List;

public interface UserService {

    List<UserRes> getAllUsers();

    UserRes getUserByEmail(String email);

    UserRes updateUserByEmail(String email, UserReq userDto);

    void deleteUserByEmail(String email);

    UserRes saveUser(CreateUserReq user);
}

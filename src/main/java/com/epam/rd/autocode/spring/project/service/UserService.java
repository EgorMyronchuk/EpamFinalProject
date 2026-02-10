package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.dto.request.user.UserReq;
import com.epam.rd.autocode.spring.project.dto.response.user.UserRes;
import com.epam.rd.autocode.spring.project.dto.response.user.UserStatusRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<UserStatusRes> getAllUsersWithStaff(Pageable pageable);

    void deleteUserByEmail(String email);

    void restore(String email);

    Long getUserIdByEmail(String email);
}

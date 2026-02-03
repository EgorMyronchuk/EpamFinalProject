package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.mapper.UserMapper;
import com.epam.rd.autocode.spring.project.dto.request.auth.CreateUserReq;
import com.epam.rd.autocode.spring.project.dto.request.user.UserReq;
import com.epam.rd.autocode.spring.project.dto.response.user.UserRes;
import com.epam.rd.autocode.spring.project.exception.AlreadyExistException;
import com.epam.rd.autocode.spring.project.exception.ExceptionConstants;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserRes saveUser(CreateUserReq user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistException(ExceptionConstants.EMAIL_EXISTS);
        }
        User entity = userMapper.toEntity(user);
        User saved = userRepository.save(entity);

        return userMapper.toDto(saved);
    }

    @Override
    public List<UserRes> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserRes getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return userMapper.toDto(user.get());
        }
        throw new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND);
    }

    @Override
    public Long getUserIdByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return user.get().getId();
        }
        throw new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND);
    }

    @Override
    public UserRes updateUserByEmail(String email, UserReq userDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));

        user.setActive(false);
        user.setDeletedAt(LocalDateTime.now());

        userRepository.save(user);
    }


}

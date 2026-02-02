package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.mapper.ClientMapper;
import com.epam.rd.autocode.spring.project.dto.mapper.ProfileMapper;
import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelReq;
import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelRes;
import com.epam.rd.autocode.spring.project.dto.response.client.ClientRes;
import com.epam.rd.autocode.spring.project.dto.response.user.UserRes;
import com.epam.rd.autocode.spring.project.exception.ExceptionConstants;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.service.ClientService;
import com.epam.rd.autocode.spring.project.service.ProfileService;
import com.epam.rd.autocode.spring.project.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final UserService userService;
    private final ClientRepository clientRepository;
    private final ProfileMapper profileMapper;

    @Override
    public ClientBusModelRes getProfileByEmail(String email) {
        Client client = clientRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));

        return profileMapper.toBusModel(client);
    }

    @Override
    @Transactional
    public ClientBusModelRes updateProfileByEmail(String email, ClientBusModelReq updateDto) {
        Client client = clientRepository.findByUserEmail(email).orElseThrow();

        client.setPhoneNumber(updateDto.getPhoneNumber());
        client.setDeliveryAddress(updateDto.getDeliveryAddress());

        client.getUser().setName(updateDto.getName());

        clientRepository.save(client);

        return profileMapper.toBusModel(client);
    }

}

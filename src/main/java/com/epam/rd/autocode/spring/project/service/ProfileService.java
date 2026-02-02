package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelReq;
import com.epam.rd.autocode.spring.project.dto.request.client.ClientBusModelRes;
import com.epam.rd.autocode.spring.project.dto.response.client.ClientRes;

public interface ProfileService {

    ClientBusModelRes getProfileByEmail (String email);

    ClientBusModelRes updateProfileByEmail(String email, ClientBusModelReq updateDto);


}

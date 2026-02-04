package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.response.client.ClientRes;
import com.epam.rd.autocode.spring.project.model.Client;

import java.math.BigDecimal;
import java.util.Locale;

public interface ClientService {

    void changeBalance(String email, BigDecimal newBalance);

    BigDecimal getBalance(String email);
}

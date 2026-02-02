package com.epam.rd.autocode.spring.project.service;

import com.epam.rd.autocode.spring.project.dto.response.client.ClientRes;
import com.epam.rd.autocode.spring.project.model.Client;

import java.math.BigDecimal;
import java.util.Locale;

public interface ClientService {

    public BigDecimal getBalanceInCurrentLocale(String email, Locale locale);

    public ClientRes getClientByEmail(String email);
}

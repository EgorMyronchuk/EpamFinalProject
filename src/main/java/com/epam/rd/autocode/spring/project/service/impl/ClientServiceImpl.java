package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.mapper.ClientMapper;
import com.epam.rd.autocode.spring.project.dto.response.client.ClientRes;
import com.epam.rd.autocode.spring.project.exception.ExceptionConstants;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.model.User;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import com.epam.rd.autocode.spring.project.repo.UserRepository;
import com.epam.rd.autocode.spring.project.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import java.util.Optional;

import static com.epam.rd.autocode.spring.project.utils.CurrencyConverter.exchangeUahToUsd;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Override
    public BigDecimal getBalanceInCurrentLocale(String email, Locale locale) {
        Client client = clientRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));

        BigDecimal balance = client.getBalance();
        if (balance == null) balance = BigDecimal.ZERO;

        if (locale.getLanguage().equals("en")) {
            return exchangeUahToUsd(balance);
        }
        return balance;
    }

    @Override
    public ClientRes getClientByEmail(String email) {
        Client client = clientRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));

        return clientMapper.toDto(client);
    }
}

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

    public void changeBalance(String email, BigDecimal newBalance) {
       Client client = clientRepository.findByUserEmail(email)
               .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));

       client.setBalance(newBalance);
       clientRepository.save(client);
    }

    public BigDecimal getBalance(String email) {
        Client client = clientRepository.findByUserEmail(email)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.EMAIL_NOT_FOUND));
        return client.getBalance();
    }
}

package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Client;
import com.epam.rd.autocode.spring.project.repo.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private final String email = "client@example.com";

    @Test
    void changeBalance_ShouldUpdateBalance_WhenClientExists() {
        Client client = new Client();
        client.setBalance(BigDecimal.ZERO);
        BigDecimal newBalance = new BigDecimal("500.50");

        when(clientRepository.findByUserEmail(email)).thenReturn(Optional.of(client));

        clientService.changeBalance(email, newBalance);

        assertEquals(newBalance, client.getBalance());
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void changeBalance_ShouldThrowException_WhenClientNotFound() {
        when(clientRepository.findByUserEmail(email)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,
                () -> clientService.changeBalance(email, BigDecimal.TEN));
        verify(clientRepository, never()).save(any());
    }

    @Test
    void getBalance_ShouldReturnCorrectBalance() {
        Client client = new Client();
        BigDecimal expectedBalance = new BigDecimal("1000.00");
        client.setBalance(expectedBalance);

        when(clientRepository.findByUserEmail(email)).thenReturn(Optional.of(client));

        BigDecimal actualBalance = clientService.getBalance(email);

        assertEquals(expectedBalance, actualBalance);
        verify(clientRepository).findByUserEmail(email);
    }

    @Test
    void getBalance_ShouldThrowException_WhenClientNotFound() {
        when(clientRepository.findByUserEmail(email)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> clientService.getBalance(email));
    }
}
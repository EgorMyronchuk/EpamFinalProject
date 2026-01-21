package com.epam.rd.autocode.spring.project.dto.mapper;

import com.epam.rd.autocode.spring.project.dto.BookItemDTO;
import com.epam.rd.autocode.spring.project.dto.ClientDTO;
import com.epam.rd.autocode.spring.project.model.BookItem;
import com.epam.rd.autocode.spring.project.model.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ClientMappper {

    private final ModelMapper mapper;

    public Client toEntity(ClientDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Client.class);
    }

    public ClientDTO toDto(Client entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, ClientDTO.class);
    }
}

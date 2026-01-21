package com.epam.rd.autocode.spring.project.dto.mapper;

import com.epam.rd.autocode.spring.project.dto.BookItemDTO;
import com.epam.rd.autocode.spring.project.model.BookItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class BookItemMapper {

    private final ModelMapper mapper;

    public BookItem toEntity(BookItemDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, BookItem.class);
    }

    public BookItemDTO toDto(BookItem entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, BookItemDTO.class);
    }
}

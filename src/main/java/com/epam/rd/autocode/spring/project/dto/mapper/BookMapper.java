package com.epam.rd.autocode.spring.project.dto.mapper;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.dto.BookItemDTO;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.BookItem;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class BookMapper {

    private final ModelMapper mapper;

    public Book toEntity(BookDTO dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Book.class);
    }

    public BookDTO toDto(Book entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, BookDTO.class);
    }
}

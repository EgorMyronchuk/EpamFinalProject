package com.epam.rd.autocode.spring.project.dto.mapper;
import com.epam.rd.autocode.spring.project.dto.BookItemDTO;
import com.epam.rd.autocode.spring.project.model.BookItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookItemMapper {

    BookItem toEntity(BookItemDTO dto);

    BookItemDTO toDto(BookItem entity);
}

package com.epam.rd.autocode.spring.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.print.Book;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookItemDTO {

    private Book book;

    private Integer quantity;
}

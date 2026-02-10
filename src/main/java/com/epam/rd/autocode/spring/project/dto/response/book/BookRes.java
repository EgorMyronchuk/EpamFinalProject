package com.epam.rd.autocode.spring.project.dto.response.book;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookRes {

    private Long id;

    private String photoUrl;

    private String author;

    private String name;

    private BigDecimal price;

}

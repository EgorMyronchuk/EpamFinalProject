package com.epam.rd.autocode.spring.project.dto.response.book;

import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookFullResp {

    private String name;

    private String genre;

    private AgeGroup ageGroup;

    private BigDecimal price;

    private LocalDate publicationDate;

    private String author;

    private Integer pages;

    private String characteristics;

    private String description;

    private Language language;


}

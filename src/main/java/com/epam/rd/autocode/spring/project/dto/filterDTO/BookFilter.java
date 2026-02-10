package com.epam.rd.autocode.spring.project.dto.filterDTO;

import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class BookFilter {
    private String query;
    private List<String> genres;
    private List<AgeGroup> ageGroups;
    private List<Language> languages;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer size = 12;
}
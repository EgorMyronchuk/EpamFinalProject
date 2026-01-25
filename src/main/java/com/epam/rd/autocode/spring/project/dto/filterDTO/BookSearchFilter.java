package com.epam.rd.autocode.spring.project.dto.filterDTO;

import java.math.BigDecimal;

public record BookSearchFilter(
        String genre,
        Integer year,
        BigDecimal minPrice,
        BigDecimal maxPrice
) {}

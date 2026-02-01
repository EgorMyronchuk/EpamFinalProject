package com.epam.rd.autocode.spring.project.dto.request.book;

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
public class BookReq {

    @NotBlank(message = "Book name must not be blank")
    @Size(max = 255, message = "Book name must be less than 255 characters")
    private String name;

    @NotBlank(message = "Genre must not be blank")
    @Size(max = 100, message = "Genre must be less than 100 characters")
    private String genre;

    @NotNull(message = "Age group must be specified")
    private AgeGroup ageGroup;

    @NotNull(message = "Price must be specified")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    @Digits(integer = 8, fraction = 2, message = "Price must have up to 8 integer digits and 2 decimal places")
    private BigDecimal price;

    @NotNull(message = "Publication date must be specified")
    @PastOrPresent(message = "Publication date cannot be in the future")
    private LocalDate publicationDate;

    @NotBlank(message = "Author name must not be blank")
    @Size(max = 255, message = "Author name must be less than 255 characters")
    private String author;

    @NotNull(message = "Number of pages must be specified")
    @Min(value = 1, message = "Book must have at least 1 page")
    @Max(value = 10000, message = "Page count is unrealistically large")
    private Integer pages;

    @Size(max = 2000, message = "Characteristics must be less than 2000 characters")
    private String characteristics;

    @Size(max = 5000, message = "Description must be less than 5000 characters")
    private String description;

    @NotNull(message = "Language must be specified")
    private Language language;

}

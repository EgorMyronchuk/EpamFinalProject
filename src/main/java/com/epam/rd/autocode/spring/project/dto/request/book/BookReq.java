package com.epam.rd.autocode.spring.project.dto.request.book;

import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookReq {

    @NotBlank(message = "{book.name.required}")
    @Size(max = 255, message = "{book.name.size}")
    private String name;

    private String photoUrl;

    @NotBlank(message = "{book.genre.required}")
    @Size(max = 100, message = "{book.genre.size}")
    private String genre;

    @NotNull(message = "{book.ageGroup.required}")
    private AgeGroup ageGroup;

    @NotNull(message = "{book.price.required}")
    @DecimalMin(value = "0.0", inclusive = false, message = "{book.price.min}")
    @Digits(integer = 8, fraction = 2, message = "{book.price.digits}")
    private BigDecimal price;

    @NotNull(message = "{book.date.required}")
    @PastOrPresent(message = "{book.date.past}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;

    @NotBlank(message = "{book.author.required}")
    @Size(max = 255, message = "{book.author.size}")
    private String author;

    @NotNull(message = "{book.pages.required}")
    @Min(value = 1, message = "{book.pages.min}")
    @Max(value = 10000, message = "{book.pages.max}")
    private Integer pages;

    @Size(max = 2000, message = "{book.characteristics.size}")
    private String characteristics;

    @Size(max = 5000, message = "{book.description.size}")
    private String description;

    @Min(value = 0, message = "{book.soldAmount.min}")
    private Integer soldAmount;

    @NotNull(message = "{book.language.required}")
    private Language language;
}

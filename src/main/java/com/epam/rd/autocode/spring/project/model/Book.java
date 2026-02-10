package com.epam.rd.autocode.spring.project.model;

import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "photo_url")
    private String photoUrl;
    private String genre;
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;
    private BigDecimal price;
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    private String author;
    @Column(name = "number_of_pages")
    private Integer pages;
    private String characteristics;
    private String description;
    @Column(name = "sold_amount")
    private Integer soldAmount;
    @Enumerated(EnumType.STRING)
    private Language language;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;

}

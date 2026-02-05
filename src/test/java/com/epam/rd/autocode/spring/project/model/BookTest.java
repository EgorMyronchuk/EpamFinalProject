package com.epam.rd.autocode.spring.project.model;

import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void testBookFieldsAndLombok() {
        Book book = new Book();
        Long id = 1L;
        String name = "The Great Gatsby";
        String photoUrl = "http://images.com/gatsby.jpg";
        String genre = "Classic";
        AgeGroup ageGroup = AgeGroup.ADULT;
        BigDecimal price = new BigDecimal("19.99");
        LocalDate pubDate = LocalDate.of(1925, 4, 10);
        String author = "F. Scott Fitzgerald";
        Integer pages = 180;
        String characteristics = "Hardcover";
        String description = "A story of the fabulously wealthy Jay Gatsby...";
        Integer soldAmount = 500;
        Language language = Language.ENGLISH;
        LocalDateTime now = LocalDateTime.now();

        book.setId(id);
        book.setName(name);
        book.setPhotoUrl(photoUrl);
        book.setGenre(genre);
        book.setAgeGroup(ageGroup);
        book.setPrice(price);
        book.setPublicationDate(pubDate);
        book.setAuthor(author);
        book.setPages(pages);
        book.setCharacteristics(characteristics);
        book.setDescription(description);
        book.setSoldAmount(soldAmount);
        book.setLanguage(language);
        book.setCreatedAt(now);
        book.setDeletedAt(null);

        assertEquals(id, book.getId());
        assertEquals(name, book.getName());
        assertEquals(photoUrl, book.getPhotoUrl());
        assertEquals(genre, book.getGenre());
        assertEquals(ageGroup, book.getAgeGroup());
        assertEquals(price, book.getPrice());
        assertEquals(pubDate, book.getPublicationDate());
        assertEquals(author, book.getAuthor());
        assertEquals(pages, book.getPages());
        assertEquals(characteristics, book.getCharacteristics());
        assertEquals(description, book.getDescription());
        assertEquals(soldAmount, book.getSoldAmount());
        assertEquals(language, book.getLanguage());
        assertEquals(now, book.getCreatedAt());
        assertNull(book.getDeletedAt());
    }

    @Test
    void testNoArgsConstructor() {
        Book book = new Book();
        assertNull(book.getId());
        assertNull(book.getName());
        assertNull(book.getPrice());
    }

    @Test
    void testAllArgsConstructor() {
        Long id = 5L;
        String name = "Test Book";
        BigDecimal price = BigDecimal.TEN;

        Book book = new Book(id, name, "url", "genre", AgeGroup.TEEN, price,
                LocalDate.now(), "Author", 300, "Chars",
                "Desc", 10, Language.UKRAINIAN,
                LocalDateTime.now(), null);

        assertEquals(id, book.getId());
        assertEquals(Language.UKRAINIAN, book.getLanguage());
        assertEquals(price, book.getPrice());
    }
}
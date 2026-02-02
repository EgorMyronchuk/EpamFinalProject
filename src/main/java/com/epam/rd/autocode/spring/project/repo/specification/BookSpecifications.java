package com.epam.rd.autocode.spring.project.repo.specification;

import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.model.enums.Language;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BookSpecifications {

    // Поиск по названию
    public Specification<Book> hasName(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    // Поиск по автору
    public Specification<Book> hasAuthor(String author) {
        return (root, query, cb) ->
                author == null ? null : cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%");
    }

    // Фильтр по списку жанров (через IN)
    public Specification<Book> hasGenres(List<String> genres) {
        return (root, query, cb) -> {
            if (genres == null || genres.isEmpty()) return null;
            return root.get("genre").in(genres);
        };
    }

    // Фильтр по возрастной группе
    public Specification<Book> hasAgeGroups(List<AgeGroup> ageGroups) {
        return (root, query, cb) -> {
            if (ageGroups == null || ageGroups.isEmpty()) return null;
            return root.get("ageGroup").in(ageGroups);
        };
    }

    // Фильтр по языку
    public Specification<Book> hasLanguages(List<Language> languages) {
        return (root, query, cb) -> {
            if (languages == null || languages.isEmpty()) return null;
            return root.get("language").in(languages);
        };
    }

    // Фильтр по диапазону цен
    public Specification<Book> priceBetween(BigDecimal min, BigDecimal max) {
        return (root, query, cb) -> {
            if (min == null && max == null) return null;
            if (min != null && max != null) return cb.between(root.get("price"), min, max);
            if (min != null) return cb.greaterThanOrEqualTo(root.get("price"), min);
            return cb.lessThanOrEqualTo(root.get("price"), max);
        };
    }

    public Specification<Book> search(String query) {
        return (root, q, cb) -> {
            if (query == null || query.isBlank()) return null;
            String pattern = "%" + query.toLowerCase() + "%";
            return cb.or(
                    cb.like(cb.lower(root.get("name")), pattern),
                    cb.like(cb.lower(root.get("author")), pattern)
            );
        };
    }
}
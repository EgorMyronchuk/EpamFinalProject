package com.epam.rd.autocode.spring.project.repo;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    Optional<Book> findByName(String name);

    Page<Book> findAllByOrderByWasSoldDesc(Pageable pageable);

    Page<Book> findAllByAgeGroup(AgeGroup ageGroup, Pageable pageable);

    Page<Book> findAllByOrderByReleaseDateDesc(Pageable pageable);
}

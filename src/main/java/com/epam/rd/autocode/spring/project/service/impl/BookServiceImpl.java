package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.filterDTO.BookFilter;
import com.epam.rd.autocode.spring.project.dto.request.book.BookReq;
import com.epam.rd.autocode.spring.project.dto.response.book.BookFullResp;
import com.epam.rd.autocode.spring.project.dto.response.book.BookRes;
import com.epam.rd.autocode.spring.project.dto.mapper.BookMapper;
import com.epam.rd.autocode.spring.project.exception.BookException;
import com.epam.rd.autocode.spring.project.exception.ExceptionConstants;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.model.enums.AgeGroup;
import com.epam.rd.autocode.spring.project.repo.BookRepository;
import com.epam.rd.autocode.spring.project.repo.specification.BookSpecifications;
import com.epam.rd.autocode.spring.project.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecifications bookSpecifications;


    @Override
    public BookRes addBook(BookReq book) {
        Book entity = bookMapper.toEntity(book);
        Book saved = bookRepository.save(entity);
        return bookMapper.toDto(saved);
    }

    @Override
    public Page<BookRes> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    @Transactional
    public BookRes updateBookById(Long id, BookReq bookReq ){
        System.out.println("New Date from Request: " + bookReq.getPublicationDate());
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id: " + id + " not found"));

        bookMapper.updateBookFromDto(bookReq, book);

        Book saved = bookRepository.save(book);

        return bookMapper.toDto(saved);
    }


    @Override
    public void deleteBookByName(String name) throws NotFoundException {
        Book book = bookRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(ExceptionConstants.NOT_FOUND));

        bookRepository.delete(book);
    }

    public List<BookRes> findBestSellers(Pageable limitTen) {

        return bookRepository.findAllByOrderBySoldAmountDesc(limitTen)
                .map(bookMapper::toDto)
                .toList();
    }

    public List<BookRes> findForChild(Pageable pageable) {
        return bookRepository
                .findAllByAgeGroup(AgeGroup.CHILD, pageable)
                .map(bookMapper::toDto)
                .toList();
    }

    public List<BookRes> findNew(Pageable pageable) {
        return bookRepository
                .findAllByOrderByPublicationDateDesc(pageable)
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public Page<BookRes> getFilteredBooks(BookFilter filter, Pageable pageable) {
        var spec = Specification.where(bookSpecifications.search(filter.getQuery()))
                .and(bookSpecifications.hasGenres(filter.getGenres()))
                .and(bookSpecifications.hasAgeGroups(filter.getAgeGroups()))
                .and(bookSpecifications.hasLanguages(filter.getLanguages()))
                .and(bookSpecifications.priceBetween(filter.getMinPrice(), filter.getMaxPrice()));

        return bookRepository.findAll(spec, pageable).map(bookMapper::toDto);
    }

    public List<String> getUniqueGenres() {
        return bookRepository.findDistinctGenres();
    }

    public BookFullResp getBookFull(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Not found book with this Id"));

        return bookMapper.toFullResp(book);
    }
}

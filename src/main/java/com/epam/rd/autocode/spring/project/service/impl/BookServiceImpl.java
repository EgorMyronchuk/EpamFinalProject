package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.request.book.BookReq;
import com.epam.rd.autocode.spring.project.dto.response.book.BookFullResp;
import com.epam.rd.autocode.spring.project.dto.response.book.BookRes;
import com.epam.rd.autocode.spring.project.dto.mapper.BookMapper;
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
    public BookRes getBookByName(String name) {
        Optional<Book> bookOpt = bookRepository.findByName(name);
        if (bookOpt.isPresent()) {
            return bookMapper.toDto(bookOpt.get());
        }
        throw new NotFoundException("Book with name :" + name + " not found");
    }

    @Override
    @Transactional
    public BookRes updateBookByName(String name, BookReq bookReq) {

        Book book = bookRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Book with name: " + name + " not found"));

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

    @Override
    public List<BookRes> findAllByAuthorAndName(String searchMessage) {
        var spec = Specification.where(bookSpecifications.hasName(searchMessage))
                .or(bookSpecifications.hasAuthor(searchMessage));

        List<Book> books = bookRepository.findAll(spec);

        if (books.isEmpty()) {
            throw new NotFoundException(ExceptionConstants.BOOK_NOT_FOUND);
        }

        return books.stream()
                .map(bookMapper::toDto)
                .toList();
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


}

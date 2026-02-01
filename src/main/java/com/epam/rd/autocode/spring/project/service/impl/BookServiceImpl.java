package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecifications bookSpecifications;


    @Override
    public BookDTO addBook(BookDTO book) {
        Book entity = bookMapper.toEntity(book);
        Book saved = bookRepository.save(entity);
        return bookMapper.toDto(saved);
    }

    @Override
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDTO getBookByName(String name) {
        Optional<Book> bookOpt = bookRepository.findByName(name);
        if (bookOpt.isPresent()) {
            return bookMapper.toDto(bookOpt.get());
        }
        throw new NotFoundException("Book with name :" + name + " not found");
    }

    @Override
    public BookDTO updateBookByName(String name, BookDTO bookDto) {
        Book book = bookRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Book with name :" + name + " not found"));

        book.setName(bookDto.getName());
        book.setGenre(bookDto.getGenre());
        book.setAgeGroup(bookDto.getAgeGroup());
        book.setPrice(bookDto.getPrice());
        book.setPublicationDate(bookDto.getPublicationDate());
        book.setAuthor(bookDto.getAuthor());
        book.setPages(bookDto.getPages());
        book.setCharacteristics(bookDto.getCharacteristics());
        book.setDescription(bookDto.getDescription());
        book.setLanguage(bookDto.getLanguage());

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
    public List<BookDTO> findAllByAuthorAndName(String searchMessage) {
        var spec = Specification.where(bookSpecifications.hasTitle(searchMessage))
                .or(bookSpecifications.hasAuthor(searchMessage));

        List<Book> books = bookRepository.findAll(spec);

        if (books.isEmpty()) {
            throw new NotFoundException(ExceptionConstants.BOOK_NOT_FOUND);
        }

        return books.stream()
                .map(bookMapper::toDto)
                .toList();
    }

    public Page<BookDTO> findBestSellers(Pageable pageable) {
        return bookRepository
                .findAllByOrderByWasSoldDesc(pageable)
                .map(bookMapper::toDto);
    }

    public Page<BookDTO> findForChild(Pageable pageable) {
        return bookRepository
                .findAllByAgeGroup(AgeGroup.CHILD, pageable)
                .map(bookMapper::toDto);
    }

    public Page<BookDTO> findNew(Pageable pageable) {
        return bookRepository
                .findAllByOrderByReleaseDateDesc(pageable)
                .map(bookMapper::toDto);
    }


}

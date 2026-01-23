package com.epam.rd.autocode.spring.project.service.impl;

import com.epam.rd.autocode.spring.project.dto.BookDTO;
import com.epam.rd.autocode.spring.project.dto.mapper.BookMapper;
import com.epam.rd.autocode.spring.project.exception.ExceptionConstants;
import com.epam.rd.autocode.spring.project.exception.NotFoundException;
import com.epam.rd.autocode.spring.project.model.Book;
import com.epam.rd.autocode.spring.project.repo.BookRepository;
import com.epam.rd.autocode.spring.project.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDTO addBook(BookDTO book) {
        Book entity = bookMapper.toEntity(book);
        Book saved = bookRepository.save(entity);
        return bookMapper.toDto(saved);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
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
}

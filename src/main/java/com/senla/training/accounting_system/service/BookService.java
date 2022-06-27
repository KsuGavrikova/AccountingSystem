package com.senla.training.accountingSystem.service;

import com.senla.training.accountingSystem.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAll();
    BookDto getBookById(Long bookId);
    BookDto create(BookDto bookDto);
    BookDto update(BookDto bookDto);
    void delete(Long bookId);

    List<BookDto> getBooksByCategory(Long categoryId);
}

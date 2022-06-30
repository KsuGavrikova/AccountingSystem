package com.senla.training.accounting_system.service;

import com.senla.training.accounting_system.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAll(Integer pageNo, Integer pageSize, String sortBy);

    BookDto getBookById(Long bookId);

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto);

    void delete(Long bookId);

    List<BookDto> getBooksByCategory(Long categoryId);
}

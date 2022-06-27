package com.senla.training.accountingSystem.controller;

import com.senla.training.accountingSystem.dto.BookDto;
import com.senla.training.accountingSystem.dto.RequestDto;
import com.senla.training.accountingSystem.service.BookService;
import com.senla.training.accountingSystem.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final RequestService requestService;

    @GetMapping()
    public List<BookDto> findAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable("id") Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    public BookDto createBook(@RequestBody BookDto bookDto) {
        return bookService.create(bookDto);
    }

    @DeleteMapping("/delete/{id}")
    public void dell(@PathVariable("id") Long id) {
        bookService.delete(id);
    }

    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        bookDto.setId(id);
        return bookService.update(bookDto);
    }

    @GetMapping("/{id}/requests")
    public List<RequestDto> history(@PathVariable("id") Long id) {
        BookDto result = bookService.getBookById(id);
        if( result!=null) {
            return requestService.getAllByBook(id);
        }
        else return new ArrayList<>();
    }
    @GetMapping("/catalog/{idCategory}")
    public List<BookDto> getBooksByCategory(@PathVariable("idCategory") Long id) {
        return bookService.getBooksByCategory(id);
    }
}

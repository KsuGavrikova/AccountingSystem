package com.senla.training.accounting_system.controller;

import com.senla.training.accounting_system.dto.BookDto;
import com.senla.training.accounting_system.dto.RequestDto;
import com.senla.training.accounting_system.service.BookService;
import com.senla.training.accounting_system.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final RequestService requestService;

    @GetMapping()
    public ResponseEntity<List<BookDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(bookService.getAll(pageNumber, pageSize, sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") @Positive Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Validated BookDto bookDto) {
        return ResponseEntity.ok(bookService.create(bookDto));
    }

    @DeleteMapping("/delete/{id}")
    public void dell(@PathVariable("id") @Positive Long id) {//переписать метод не должен удалять
        bookService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable @Positive Long id, @RequestBody BookDto bookDto) {
        bookDto.setId(id);
        return ResponseEntity.ok(bookService.update(bookDto));
    }

    //TODO добавить пагинацию
    @GetMapping("/{id}/requests")
    public ResponseEntity<List<RequestDto>> history(@PathVariable("id") @Positive Long id) {
        BookDto result = bookService.getBookById(id);
        if (result != null) {
            return ResponseEntity.ok(requestService.getAllByBook(id));
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //TODO добавить пагинацию
    @GetMapping("/catalog/{idCategory}")
    public ResponseEntity<List<BookDto>> getBooksByCategory(@PathVariable("idCategory") Long id) {
        return ResponseEntity.ok(bookService.getBooksByCategory(id));
    }
}

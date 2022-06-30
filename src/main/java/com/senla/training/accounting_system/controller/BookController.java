package com.senla.training.accounting_system.controller;

import com.senla.training.accounting_system.dto.BookDto;
import com.senla.training.accounting_system.dto.RequestDto;
import com.senla.training.accounting_system.service.BookService;
import com.senla.training.accounting_system.service.RequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RequestMapping("/books")
@RestController
public class BookController {

    private final BookService bookService;
    private final RequestService requestService;

    @GetMapping()
    public ResponseEntity<List<BookDto>> findAll(@RequestParam(defaultValue = "0") Integer pageNo,
                                                 @RequestParam(defaultValue = "10") Integer pageSize,
                                                 @RequestParam(defaultValue = "id") String sortBy) {
        return ResponseEntity.ok(bookService.getAll(pageNo, pageSize, sortBy));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.create(bookDto));
    }

    @DeleteMapping("/delete/{id}")
    public void dell(@PathVariable("id") Long id) {//переписать метод не должен удалять
        bookService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable Long id, @RequestBody BookDto bookDto) {
        bookDto.setId(id);
        return ResponseEntity.ok(bookService.update(bookDto));
    }

    //TODO добавить пагинацию?
    @GetMapping("/{id}/requests")
    public ResponseEntity<List<RequestDto>> history(@PathVariable("id") Long id) {
        BookDto result = bookService.getBookById(id);
        if (result != null) {
            return ResponseEntity.ok(requestService.getAllByBook(id));
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //TODO добавить пагинацию?
    @GetMapping("/catalog/{idCategory}")
    public ResponseEntity<List<BookDto>> getBooksByCategory(@PathVariable("idCategory") Long id) {
        return ResponseEntity.ok(bookService.getBooksByCategory(id));
    }
}

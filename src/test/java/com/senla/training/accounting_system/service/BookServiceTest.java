package com.senla.training.accounting_system.service;

import com.senla.training.accounting_system.dto.BookDto;
import com.senla.training.accounting_system.mapper.BookMapper;
import com.senla.training.accounting_system.model.Book;
import com.senla.training.accounting_system.model.Category;
import com.senla.training.accounting_system.repository.BookRepository;
import com.senla.training.accounting_system.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks
    BookServiceImpl bookService;
    @Mock
    BookRepository bookRepository;
    @Mock
    BookMapper bookMapper;
    @Mock
    Category category;

    private Long id1;
    private Long id2;
    private BookDto bookDto1;
    private BookDto bookDto2;

    private Book book1;
    private Book book2;
    private List<Book> books;


    @BeforeEach
    public void before() {
        id1 = 1L;
        id2 = 2L;
        bookDto1 = new BookDto();
        bookDto1.setId(id1);
        bookDto2 = new BookDto();
        bookDto2.setId(id2);
        List<BookDto> listBookDto = new ArrayList<>();
        listBookDto.add(bookDto1);
        listBookDto.add(bookDto2);

        book1 = new Book();
        book1.setId(id1);
        book1.setName("book1");
        book1.setPublicationYear(2022);
        book1.setCategory(category);
        book1.setAuthors(new ArrayList<>());
        book2 = new Book();
        book2.setId(id2);
        books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
    }

    @Test
    void testGetAll() {
        // given
        Page<Book> page = new PageImpl<>(books);
        when(bookRepository.findAll(PageRequest.of(0, 2, Sort.by("Id")))).thenReturn(page);
        when(bookMapper.entityToDto(book1)).thenReturn(bookDto1);
        when(bookMapper.entityToDto(book2)).thenReturn(bookDto2);
        // when
        List<BookDto> result = bookService.getAll(0, 2, "Id");
        // then
        verify(bookRepository, times(1)).findAll(PageRequest.of(0, 2, Sort.by("Id")));
        assertNotNull(result);
        assertEquals(id1, result.get(0).getId());
        assertEquals(id2, result.get(1).getId());
    }

    @Test
    void testGetAllEmpty() {
        // given
        when(bookRepository.findAll(PageRequest.of(0, 1, Sort.by("Id")))).thenReturn(Page.empty());
        // when
        List<BookDto> result = bookService.getAll(0, 1, "Id");
        // then
        verify(bookRepository, times(1)).findAll(PageRequest.of(0, 1, Sort.by("Id")));
        assertNotNull(result);
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testCreate() {
        // given
        when(bookRepository.save(book1)).thenReturn(book1);
        when(bookMapper.dtoToEntity(bookDto1)).thenReturn(book1);
        when(bookMapper.entityToDto(book1)).thenReturn(bookDto1);
        // when
        BookDto result = bookService.create(bookDto1);
        // then
        verify(bookRepository, times(1)).save(book1);
        assertNotNull(result);
        assertEquals(bookDto1.getId(), result.getId());
    }

    @Test
    void testRead() {
        // given
        when(bookRepository.getById(id1)).thenReturn(book1);
        when(bookMapper.entityToDto(book1)).thenReturn(bookDto1);
        // when
        BookDto result = bookService.getBookById(id1);
        // then
        verify(bookRepository, times(1)).getById(id1);
        assertNotNull(result);
        assertEquals(book1.getId(), result.getId());
    }

    @Test
    void testReadNull() {
        // given
        when(bookRepository.getById(id1)).thenReturn(null);
        // when
        BookDto result = bookService.getBookById(id1);
        // then
        verify(bookRepository, times(1)).getById(id1);
        assertNull(result);
    }

    @Test
    void testUpdate() {
        // given
        when(bookRepository.existsById(book1.getId())).thenReturn(true);
        when(bookRepository.save(book1)).thenReturn(book1);
        when(bookMapper.dtoToEntity(bookDto1)).thenReturn(book1);
        when(bookMapper.entityToDto(book1)).thenReturn(bookDto1);
        // when
        BookDto result = bookService.update(bookDto1);
        //then
        verify(bookRepository, times(1)).save(book1);
        assertNotNull(result);
    }

    @Test
    void testDelete() {
        // when
        bookService.delete(id1);
        // then
        verify(bookRepository, times(1)).deleteById(id1);
    }
}

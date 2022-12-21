//package com.senla.training.accounting_system.service;
//
//import com.senla.training.accounting_system.dto.BookDto;
//import com.senla.training.accounting_system.mapper.BookMapper;
//import com.senla.training.accounting_system.model.Book;
//import com.senla.training.accounting_system.model.Category;
//import com.senla.training.accounting_system.model.History;
//import com.senla.training.accounting_system.repository.HistoryRepository;
//import com.senla.training.accounting_system.service.impl.HistoryServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Sort;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//class HistoryServiceTest {
//    @InjectMocks
//    HistoryServiceImpl historyService;
//    @Mock
//    HistoryRepository historyRepository;
//    @Mock
//    BookMapper bookMapper;
//    @Mock
//    Category category;
//
//    private Long id1;
//    private Long id2;
//    private BookDto bookDto1;
//    private BookDto bookDto2;
//
//    private Book book1;
//    private Book book2;
//    //  private List<Book> books;
//
//    private History historyForBook1;
//    private History historyForBook2;
//    private List<History> history;
//
//    @BeforeEach
//    public void before() {
//        id1 = 1L;
//        id2 = 2L;
//        bookDto1 = new BookDto();
//        bookDto1.setId(id1);
//        bookDto2 = new BookDto();
//        bookDto2.setId(id2);
//        //  List<BookDto> listBookDto = new ArrayList<>();
//        //  listBookDto.add(bookDto1);
//        //  listBookDto.add(bookDto2);
//
//        book1 = new Book();
//        book1.setId(id1);
//        book1.setName("book1");
//        book1.setPublicationYear(2022);
//        book1.setCategory(category);
//        book1.setAuthors(new ArrayList<>());
//        book2 = new Book();
//        book2.setId(id2);
//        //   books = new ArrayList<>();
//        //  books.add(book1);
//        // books.add(book2);
//
//        historyForBook1 = new History(book1, LocalDate());
//
//    }
//
//    @Test
//    void getAll() {
//        // given
//        Page<Book> page = new PageImpl<>(books);
//        when(bookRepository.findAll(PageRequest.of(0, 2, Sort.by("Id")))).thenReturn(page);
//        when(bookMapper.entityToDto(book1)).thenReturn(bookDto1);
//        when(bookMapper.entityToDto(book2)).thenReturn(bookDto2);
//        // when
//        List<BookDto> result = bookService.getAll(0, 2, "Id");
//        // then
//        verify(bookRepository, times(1)).findAll(PageRequest.of(0, 2, Sort.by("Id")));
//        assertNotNull(result);
//        assertEquals(id1, result.get(0).getId());
//        assertEquals(id2, result.get(1).getId());
//    }
//
//    @Test
//    void getRent() {
//    }
//
//    @Test
//    void getOverdue() {
//    }
//
//    @Test
//    void getWasReturn() {
//    }
//}
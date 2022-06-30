package com.senla.training.accounting_system.service;

import com.senla.training.accounting_system.dto.author.AuthorDto;
import com.senla.training.accounting_system.exeprion.NoEntityException;
import com.senla.training.accounting_system.exeprion.RepositoryException;
import com.senla.training.accounting_system.mapper.AuthorMapper;
import com.senla.training.accounting_system.model.Author;
import com.senla.training.accounting_system.repository.AuthorRepository;
import com.senla.training.accounting_system.service.impl.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @InjectMocks
    AuthorServiceImpl authorService;

    @Mock
    AuthorRepository authorRepository;

    @Mock
    AuthorMapper authorMapper;

    private Long id1;
    private Long id2;
    private Author author1;
    private Author author2;
    private List<Author> authors;

    private AuthorDto authorDto1;
    private AuthorDto authorDto2;
    private List<AuthorDto> authorDtos;

    @BeforeEach
    public void before() {
        id1 = 1L;
        id2 = 2L;
        author1 = new Author();
        author2 = new Author();
        author1.setId(id1);
        author2.setId(id2);
        authors = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);

        authorDto1 = new AuthorDto();
        authorDto2 = new AuthorDto();
        authorDto1.setId(id1);
        authorDto2.setId(id2);
        authorDtos = new ArrayList<>();
        authors.add(author1);
        authors.add(author2);
    }

    @Test
    void testGetAll() {
        //given
        when(authorRepository.findAll()).thenReturn(authors);
        when(authorMapper.entityToDto(author1)).thenReturn(authorDto1);
        when(authorMapper.entityToDto(author2)).thenReturn(authorDto2);
        //when
        List<AuthorDto> result = authorService.getAll();
        //then
        verify(authorRepository, times(1)).findAll();
        assertNotNull(result);
        assertEquals(id1, result.get(0).getId());
        assertEquals(id2, result.get(1).getId());
    }

    @Test
    void testGetAllEmpty() {
        // given
        when(authorRepository.findAll()).thenReturn(new ArrayList<>());
        // when
        List<AuthorDto> result = authorService.getAll();
        // then
        verify(authorRepository, times(1)).findAll();
        assertNotNull(result);
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testGetById() {
        // given
        when(authorRepository.findById(id1)).thenReturn(Optional.of(author1));
        when(authorMapper.entityToDto(author1)).thenReturn(authorDto1);
        // when
        AuthorDto result = authorService.getById(id1);
        // then
        verify(authorRepository, times(1)).findById(id1);
        assertNotNull(result);
        assertEquals(author1.getId(), result.getId());
    }

    @Test
    void testExpectedException() {

        NumberFormatException thrown = assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("One");
        }, "NumberFormatException was expected");

        assertEquals("For input string: \"One\"", thrown.getMessage());
    }

    @Test
    void testGetByIdNull() {
        //given
        when(authorRepository.findById(3L)).thenThrow(NoEntityException.class);
        //then
        assertThrows(RepositoryException.class, () -> {
            authorService.getById(3L);
        });
    }
}
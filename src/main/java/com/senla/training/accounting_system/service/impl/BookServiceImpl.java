package com.senla.training.accounting_system.service.impl;

import com.senla.training.accounting_system.dto.BookDto;
import com.senla.training.accounting_system.exeprion.NoEntityException;
import com.senla.training.accounting_system.exeprion.RepositoryException;
import com.senla.training.accounting_system.mapper.BookMapper;
import com.senla.training.accounting_system.model.Book;
import com.senla.training.accounting_system.model.Category;
import com.senla.training.accounting_system.repository.BookRepository;
import com.senla.training.accounting_system.repository.CategoryRepository;
import com.senla.training.accounting_system.service.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookDto> getAll(Integer pageNo, Integer pageSize, String sortBy) {
        Page<Book> books = null;
        try {
            Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
            books = bookRepository.findAll(paging);
            log.info("IN getAll -  Books was found");
        } catch (Exception e) {
            log.error("IN getAll - Books Repository Exception");
            throw new RepositoryException("IN getAll - Books Repository Exception" + e);
        }
        if (books.isEmpty()) {
            log.info("IN getAll - Books is empty");
            return new ArrayList<>();
        }
        return books.getContent().stream()
                .map(bookMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long bookId) {
        Book book;
        try {
            book = bookRepository.getById(bookId);
        } catch (Exception e) {
            log.error("IN getBookById -  Repository Exception");
            throw new RepositoryException("IN getBookById - " + e);
        }
        return bookMapper.entityToDto(book);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        //TODO validate метод для проверки всех полей бук
        Book book = bookMapper.dtoToEntity(bookDto);
        return bookMapper.entityToDto(bookRepository.save(book));
    }

    @Override
    public BookDto update(BookDto bookDto) {
        if (bookRepository.existsById(bookDto.getId())) {
            log.info("IN update - Book for update was found");
            Book book = bookMapper.dtoToEntity(bookDto);
            return bookMapper.entityToDto(bookRepository.save(book));
        } else {
            log.info("IN update - Book for update not found" + bookDto);
            throw new NoEntityException();
        }
    }

    @Override
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
        log.info("IN delete - Book with id {} was delete", bookId);
    }

    @Override
    public List<BookDto> getBooksByCategory(Long categoryId) {
        List<BookDto> listBooks = new ArrayList<>();
        if (categoryRepository.getById(categoryId).getParentId() == null) {
            listBooks = bookMapper.mapBooksToBooksDto(bookRepository.findAllByCategoryId(categoryId));
        } else {
            List<Category> categoryList = categoryRepository.findAll();
            Set<Category> childCategories = new HashSet<>();
            childCategories.add(categoryRepository.getById(categoryId));
            getAllChildCategory(categoryList, childCategories);
            for (Category childCat : childCategories) {
                listBooks.addAll(bookMapper.mapBooksToBooksDto(
                        bookRepository.findAllByCategoryId(childCat.getId())));
            }
        }
        return listBooks;
    }

    private void getAllChildCategory(List<Category> categoryList, Set<Category> childCategories) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (Category category : categoryList) {
                for (Category child : childCategories) {
                    if (category.getParentId().equals(child.getId()) && !childCategories.contains(category)) {
                        childCategories.add(category);
                        flag = true;
                    }
                }
            }
        }
    }

}

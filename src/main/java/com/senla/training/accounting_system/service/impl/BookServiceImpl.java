package com.senla.training.accountingSystem.service.impl;

import com.senla.training.accountingSystem.dto.BookDto;
import com.senla.training.accountingSystem.mapper.BookMapper;
import com.senla.training.accountingSystem.model.Book;
import com.senla.training.accountingSystem.model.Category;
import com.senla.training.accountingSystem.repository.BookRepository;
import com.senla.training.accountingSystem.repository.CategoryRepository;
import com.senla.training.accountingSystem.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    CategoryRepository categoryRepository;

    BookMapper bookMapper;

    @Override
    public List<BookDto> getAll() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            return new ArrayList<>();
        }
        return books.stream()
                .map(bookMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);
        return bookMapper.entityToDto(book);
    }

    @Override
    public BookDto create(BookDto bookDto) {
        //validate метод для проверки всех полей бук
        Book book = bookMapper.dtoToEntity(bookDto);
        return bookMapper.entityToDto(bookRepository.save(book));
    }

    @Override
    public BookDto update(BookDto bookDto) {
        if (bookRepository.existsById(bookDto.getId())) {
            Book book = bookMapper.dtoToEntity(bookDto);
            return bookMapper.entityToDto(bookRepository.save(book));
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public void delete(Long bookId) {
        bookRepository.deleteById(bookId);
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
                listBooks.addAll(bookMapper.mapBooksToBooksDto(bookRepository.findAllByCategoryId(childCat.getId())));
            }
        }
        return listBooks;
    }

    private  void getAllChildCategory(List<Category> categoryList, Set<Category> childCategories) {
        boolean flag = true;
        while (flag) {
            flag = false;
            for (Category category : categoryList) {
                for (Category child : childCategories) {
                    if (category.getParentId() == child.getId() && !childCategories.contains(category)) {
                        childCategories.add(category);
                        flag = true;
                    }
                }
            }
        }
    }

}

package com.senla.training.accountingSystem.mapper;

import com.senla.training.accountingSystem.dto.AuthorNameDto;
import com.senla.training.accountingSystem.dto.BookDto;
import com.senla.training.accountingSystem.model.Author;
import com.senla.training.accountingSystem.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {CategoryMapper.class, AuthorMapper.class},
        componentModel = "spring")
public interface BookMapper {
    BookDto entityToDto(Book entity);

    Book dtoToEntity(BookDto dto);

    List<BookDto> mapBooksToBooksDto(List<Book> books);

    List<AuthorNameDto> map(List<Author> authors);

}

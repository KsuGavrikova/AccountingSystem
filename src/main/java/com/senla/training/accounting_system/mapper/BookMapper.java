package com.senla.training.accounting_system.mapper;

import com.senla.training.accounting_system.dto.BookDto;
import com.senla.training.accounting_system.dto.author.AuthorDto;
import com.senla.training.accounting_system.model.Author;
import com.senla.training.accounting_system.model.Book;
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

    List<AuthorDto.AuthorNameDto> map(List<Author> authors);

}

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
        componentModel = "spring")
public interface AuthorMapper {
    AuthorDto entityToDto(Author entity);

    Author dtoToEntity(AuthorDto dto);

    Author nameDtoToEntity(AuthorDto.AuthorNameDto entity);

    AuthorDto.AuthorNameDto entityToNameDto(Author entity);

    List<BookDto> map(List<Book> books);
}

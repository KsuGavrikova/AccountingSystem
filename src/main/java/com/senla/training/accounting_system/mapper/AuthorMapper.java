package com.senla.training.accountingSystem.mapper;

import com.senla.training.accountingSystem.dto.AuthorDto;
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
        //  uses = BookMapper.class,
        componentModel = "spring")
public interface AuthorMapper {
    AuthorDto entityToDto(Author entity);

    Author dtoToEntity(AuthorDto dto);

    Author nameDtoToEntity(AuthorNameDto entity);

    AuthorNameDto entityToNameDto(Author entity);

    List<BookDto> map(List<Book> books);
}

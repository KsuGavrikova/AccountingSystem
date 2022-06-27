package com.senla.training.accounting_system.dto;

import com.senla.training.accounting_system.dto.author.AuthorDto;
import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private Long id;

    private String name;

    private Integer publicationYear;

    private String rent;

    private CategoryDto category;

    private List<AuthorDto.AuthorNameDto> authors;
}

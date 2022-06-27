package com.senla.training.accountingSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDto {
    private Long id;

    private String name;

    private Integer publicationYear;

    private String rent;

    private CategoryDto category;

    private List<AuthorNameDto> authors;
}

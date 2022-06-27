package com.senla.training.accounting_system.dto.author;

import com.senla.training.accounting_system.dto.BookDto;
import lombok.Data;

import java.util.List;

@Data
public class AuthorDto {
    private Long id;

    private String name;

    private Integer birthDate;

    private String about;

    private List<BookDto> books;

    @Data
    public static class AuthorNameDto {
        private Long id;

        private String name;

        private Integer birthDate;

        private String about;
    }
}

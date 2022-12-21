package com.senla.training.accounting_system.dto.author;

import com.senla.training.accounting_system.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Long id;

    private String name;

    @Pattern(regexp = "^[1-9][0-9]{3}$")
    private Integer birthDate;

    private String about;

    private List<BookDto> books;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AuthorNameDto {
        private Long id;

        private String name;

        @Pattern(regexp = "^[1-9][0-9]{3}$")
        private Integer birthDate;

        private String about;
    }
}

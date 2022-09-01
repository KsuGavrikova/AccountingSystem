package com.senla.training.accounting_system.dto;

import com.senla.training.accounting_system.dto.author.AuthorDto;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private Long id;

    @NotBlank
    private String name;

    @Pattern(regexp="^[1-9][0-9]{3}$")
    private Integer publicationYear;

    @Pattern(regexp = "[0,1]")
    private String rent;

    private CategoryDto category;

    private List<AuthorDto.AuthorNameDto> authors;
}

package com.senla.training.accountingSystem.mapper;

import com.senla.training.accountingSystem.dto.CategoryDto;
import com.senla.training.accountingSystem.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface  CategoryMapper {
    CategoryDto entityToDto(Category entity);

    Category dtoToEntity(CategoryDto dto);
}

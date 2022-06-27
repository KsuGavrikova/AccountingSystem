package com.senla.training.accountingSystem.mapper;

import com.senla.training.accountingSystem.dto.UserDto;
import com.senla.training.accountingSystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface UserMapper {
    UserDto entityToDto(User entity);

    User dtoToEntity(UserDto dto);
}

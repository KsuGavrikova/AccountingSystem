package com.senla.training.accounting_system.mapper;

import com.senla.training.accounting_system.dto.user.UserDto;
import com.senla.training.accounting_system.model.User;
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

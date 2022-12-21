package com.senla.training.accounting_system.mapper;

import com.senla.training.accounting_system.dto.user.AdminUserDto;
import com.senla.training.accounting_system.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface AdminUserMapper {

    AdminUserDto entityToDto(User entity);

    User dtoToEntity(AdminUserDto dto);
}

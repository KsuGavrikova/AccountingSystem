package com.senla.training.accountingSystem.mapper;

import com.senla.training.accountingSystem.dto.RequestDto;
import com.senla.training.accountingSystem.model.Request;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {BookMapper.class, UserMapper.class},
        componentModel = "spring")
public interface RequestMapper {

    RequestDto entityToDto(Request entity);

    Request dtoToEntity(RequestDto dto);
}

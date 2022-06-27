package com.senla.training.accountingSystem.mapper;

import com.senla.training.accountingSystem.dto.HistoryDto;
import com.senla.training.accountingSystem.model.History;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {BookMapper.class, UserMapper.class},
        componentModel = "spring")
public interface HistoryMapper {
    HistoryDto entityToDto(History entity);

    History dtoToEntity(HistoryDto dto);
}

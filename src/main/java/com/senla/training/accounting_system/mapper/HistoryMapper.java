package com.senla.training.accounting_system.mapper;

import com.senla.training.accounting_system.dto.HistoryDto;
import com.senla.training.accounting_system.model.History;
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

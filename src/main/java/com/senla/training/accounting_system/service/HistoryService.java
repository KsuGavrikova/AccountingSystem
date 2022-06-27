package com.senla.training.accounting_system.service;

import com.senla.training.accounting_system.dto.BookDto;
import com.senla.training.accounting_system.dto.HistoryDto;

import java.util.List;
import java.util.Set;

public interface HistoryService {
    List<HistoryDto> getAll();

    List<HistoryDto> getRent();

    List<HistoryDto> getOverdue();

    Set<BookDto> getWasReturn();
}

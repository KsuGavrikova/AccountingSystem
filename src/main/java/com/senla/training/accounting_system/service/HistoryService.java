package com.senla.training.accountingSystem.service;

import com.senla.training.accountingSystem.dto.BookDto;
import com.senla.training.accountingSystem.dto.HistoryDto;

import java.util.List;
import java.util.Set;

public interface HistoryService {
    List<HistoryDto> getAll();
    List<HistoryDto> getRent();
    List<HistoryDto> getOverdue();

    Set<BookDto> getWasReturn();
}

package com.senla.training.accountingSystem.service.impl;

import com.senla.training.accountingSystem.dto.BookDto;
import com.senla.training.accountingSystem.dto.HistoryDto;
import com.senla.training.accountingSystem.mapper.BookMapper;
import com.senla.training.accountingSystem.mapper.HistoryMapper;
import com.senla.training.accountingSystem.model.History;
import com.senla.training.accountingSystem.repository.HistoryRepository;
import com.senla.training.accountingSystem.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    HistoryMapper historyMapper;
    @Autowired
    BookMapper bookMapper;
    @Override
    public List<HistoryDto> getAll() {
        List<History> history = historyRepository.findAll();
        if (history.isEmpty()) {
            return new ArrayList<>();
        }
        return history.stream()
                .map(historyMapper::entityToDto)
                .collect(Collectors.toList());
    }
    public Set<BookDto> getWasReturn(){
        List<History> history = historyRepository.findAll();
        if (history.isEmpty()) {
            return new HashSet<>();
        }
        return history.stream()
                .filter(h -> h.getDateEnd() != null)
                .map(h->h.getBook())
                .map(bookMapper::entityToDto)
                .collect(Collectors.toSet());
    }

    @Override
    public List<HistoryDto> getRent() {
        List<History> history = historyRepository.findAll();
        if (history.isEmpty()) {
            return new ArrayList<>();
        }
        return history.stream()
                .filter(h -> h.getDateEnd() == null)
                .map(historyMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoryDto> getOverdue() {
        List<History> history = historyRepository.findAll();
        if (history.isEmpty()) {
            return new ArrayList<>();
        }
        return history.stream()
                .filter(h -> h.getDateEnd() != null && h.getDateToReturn().compareTo(h.getDateEnd()) < 0)
                .map(historyMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

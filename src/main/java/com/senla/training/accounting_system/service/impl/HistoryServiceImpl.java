package com.senla.training.accounting_system.service.impl;

import com.senla.training.accounting_system.dto.BookDto;
import com.senla.training.accounting_system.dto.HistoryDto;
import com.senla.training.accounting_system.exeprion.RepositoryException;
import com.senla.training.accounting_system.mapper.BookMapper;
import com.senla.training.accounting_system.mapper.HistoryMapper;
import com.senla.training.accounting_system.model.History;
import com.senla.training.accounting_system.repository.HistoryRepository;
import com.senla.training.accounting_system.service.HistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;
    private final BookMapper bookMapper;

    private static final String IS_EMPTY = "History is empty";

    private List<History> findAll() {
        List<History> history;
        try {
            history = historyRepository.findAll();
            log.info("IN findAll - History was found");
        } catch (Exception e) {
            log.error("IN findAll - History Repository Exception ");
            throw new RepositoryException("IN findAll - History " + e);
        }
        return history;
    }

    @Override
    public List<HistoryDto> getAll() {
        List<History> history = findAll();
        if (history.isEmpty()) {
            log.info("IN getAll -" + IS_EMPTY);
            return new ArrayList<>();
        } else {
            return history.stream()
                    .map(historyMapper::entityToDto)
                    .collect(Collectors.toList());
        }
    }

    public Set<BookDto> getWasReturn() {
        List<History> history = findAll();
        if (history.isEmpty()) {
            log.info("IN getWasReturn -" + IS_EMPTY);
            return new HashSet<>();
        } else {
            return history.stream()
                    .filter(h -> h.getDateEnd() != null)
                    .map(History::getBook)
                    .map(bookMapper::entityToDto)
                    .collect(Collectors.toSet());
        }
    }

    @Override
    public List<HistoryDto> getRent() {
        List<History> history = findAll();
        if (history.isEmpty()) {
            log.info("IN getRent -" + IS_EMPTY);
            return new ArrayList<>();
        } else {
            return history.stream()
                    .filter(h -> h.getDateEnd() == null)
                    .map(historyMapper::entityToDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public List<HistoryDto> getOverdue() {
        List<History> history = findAll();
        if (history.isEmpty()) {
            log.info("IN getRent -" + IS_EMPTY);
            return new ArrayList<>();
        } else {
            return history.stream()
                    .filter(h -> h.getDateEnd() != null && h.getDateToReturn().compareTo(h.getDateEnd()) < 0)
                    .map(historyMapper::entityToDto)
                    .collect(Collectors.toList());
        }
    }
}

package com.senla.training.accountingSystem.controller;

import com.senla.training.accountingSystem.dto.BookDto;
import com.senla.training.accountingSystem.dto.HistoryDto;
import com.senla.training.accountingSystem.service.HistoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/history")
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping()
    public List<HistoryDto> findAll() {
        return historyService.getAll();
    }

    @GetMapping("/rent")
    public List<HistoryDto> findRent() {
        return historyService.getRent();
    }

    @GetMapping("/overdue")
    public List<HistoryDto> findOverdue() {
        return historyService.getOverdue();
    }

    @GetMapping("/wasReturn")
    public Set<BookDto> findWasReturn() {
        return historyService.getWasReturn();
    }
}

package com.senla.training.accounting_system.controller;

import com.senla.training.accounting_system.dto.BookDto;
import com.senla.training.accounting_system.dto.HistoryDto;
import com.senla.training.accounting_system.service.HistoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@AllArgsConstructor
@RequestMapping("/history")
@RestController
public class HistoryController {

    private final HistoryService historyService;

    @GetMapping()
    public ResponseEntity<List<HistoryDto>> findAll() {
        return ResponseEntity.ok(historyService.getAll());
    }

    @GetMapping("/rent")
    public ResponseEntity<List<HistoryDto>> findRent() {
        return ResponseEntity.ok(historyService.getRent());
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<HistoryDto>> findOverdue() {
        return ResponseEntity.ok(historyService.getOverdue());
    }

    @GetMapping("/wasReturn")
    public ResponseEntity<Set<BookDto>> findWasReturn() {
        return ResponseEntity.ok(historyService.getWasReturn());
    }
}

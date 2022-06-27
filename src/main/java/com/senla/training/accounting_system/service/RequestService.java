package com.senla.training.accountingSystem.service;

import com.senla.training.accountingSystem.dto.RequestDto;

import java.util.List;

public interface RequestService {
    List<RequestDto> getAllByBook(Long bookId);
}

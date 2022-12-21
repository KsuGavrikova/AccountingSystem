package com.senla.training.accounting_system.service;

import com.senla.training.accounting_system.dto.RequestDto;

import java.util.List;

public interface RequestService {

    List<RequestDto> getAllByBook(Long bookId);

}

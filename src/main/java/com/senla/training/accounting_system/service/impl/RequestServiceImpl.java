package com.senla.training.accounting_system.service.impl;

import com.senla.training.accounting_system.dto.RequestDto;
import com.senla.training.accounting_system.exeprion.RepositoryException;
import com.senla.training.accounting_system.mapper.RequestMapper;
import com.senla.training.accounting_system.model.Request;
import com.senla.training.accounting_system.repository.RequestRepository;
import com.senla.training.accounting_system.service.RequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    @Override
    public List<RequestDto> getAllByBook(Long bookId) {
        List<Request> requests;
        try {
            requests = requestRepository.findAll();
            log.info("IN getAllByBook - Requests was found");
        } catch (Exception e) {
            log.error("IN getAllByBook - Requests Repository Exception");
            throw new RepositoryException("IN getAllByBook - Requests " + e);
        }
        if (requests.isEmpty()) {
            log.info("IN getAllByBook - Requests is empty");
            return new ArrayList<>();
        }
        return requests.stream()
                .filter(request -> Objects.equals(request.getBook().getId(), bookId))
                .map(requestMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

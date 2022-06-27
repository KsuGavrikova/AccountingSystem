package com.senla.training.accountingSystem.service.impl;

import com.senla.training.accountingSystem.dto.RequestDto;
import com.senla.training.accountingSystem.mapper.RequestMapper;
import com.senla.training.accountingSystem.model.Request;
import com.senla.training.accountingSystem.repository.RequestRepository;
import com.senla.training.accountingSystem.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    @Override
    public List<RequestDto> getAllByBook(Long bookId) {
        List<Request> requests = requestRepository.findAll();
        if (requests.isEmpty()) {
            return new ArrayList<>();
        }
        return requests.stream()
                .filter(request -> Objects.equals(request.getBook().getId(), bookId))
                .map(requestMapper::entityToDto)
                .collect(Collectors.toList());
    }
}

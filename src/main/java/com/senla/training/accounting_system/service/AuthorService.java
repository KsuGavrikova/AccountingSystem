package com.senla.training.accountingSystem.service;

import com.senla.training.accountingSystem.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAll();
    AuthorDto read(Long authorId);
}

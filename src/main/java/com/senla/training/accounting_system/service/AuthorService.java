package com.senla.training.accounting_system.service;

import com.senla.training.accounting_system.dto.author.AuthorDto;

import java.util.List;

public interface AuthorService {

    List<AuthorDto> getAll();

    AuthorDto getById(Long authorId);
}

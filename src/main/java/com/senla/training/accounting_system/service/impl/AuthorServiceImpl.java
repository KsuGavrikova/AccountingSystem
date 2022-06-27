package com.senla.training.accountingSystem.service.impl;

import com.senla.training.accountingSystem.dto.AuthorDto;
import com.senla.training.accountingSystem.mapper.AuthorMapper;
import com.senla.training.accountingSystem.model.Author;
import com.senla.training.accountingSystem.repository.AuthorRepository;
import com.senla.training.accountingSystem.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.findAll();
        if (authors.isEmpty()) {
            return new ArrayList<>();
        } else {
            return authors.stream()
                    .map(author -> authorMapper.entityToDto(author))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public AuthorDto read(Long authorId) {
        Author author = authorRepository.findById(authorId).orElse(null);
        if (author == null) {
            return null;
        }
        return authorMapper.entityToDto(author);
    }
}

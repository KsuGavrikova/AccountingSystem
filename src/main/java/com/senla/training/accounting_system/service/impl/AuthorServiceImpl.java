package com.senla.training.accounting_system.service.impl;

import com.senla.training.accounting_system.dto.author.AuthorDto;
import com.senla.training.accounting_system.exeprion.NoEntityException;
import com.senla.training.accounting_system.exeprion.RepositoryException;
import com.senla.training.accounting_system.mapper.AuthorMapper;
import com.senla.training.accounting_system.model.Author;
import com.senla.training.accounting_system.repository.AuthorRepository;
import com.senla.training.accounting_system.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors;
        try {
            authors = authorRepository.findAll();
            log.info("IN getAll - Authors was found");
        } catch (Exception e) {
            log.error("IN getAll - authors  Repository Exception ");
            throw new RepositoryException("IN getAll - authors Repository Exception " + e);
        }
        if (authors.isEmpty()) {
            log.info("IN getAll - Authors is empty");
            return new ArrayList<>();
        } else {
            return authors.stream()
                    .map(authorMapper::entityToDto)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public AuthorDto getById(Long authorId) {
        Author author;
        try {
            author = authorRepository.findById(authorId)
                    .orElseThrow(() -> new NoEntityException("Author with id ", authorId));
            log.info("IN getById - Author with id " + authorId + " was found");
        } catch (Exception e) {
            log.error("IN getById - Repository Exception in getById author");
            throw new RepositoryException("Author findById " + e);
        }
        return authorMapper.entityToDto(author);
    }
}

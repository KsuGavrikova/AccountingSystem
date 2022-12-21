package com.senla.training.accounting_system.repository;

import com.senla.training.accounting_system.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsById(Long bookId);

    Page<Book> findAll(Pageable pageable);

    //Page<Book> findAllByCategoryId(Long categoryId, Pageable pageable);
    List<Book> findAllByCategoryId(Long categoryId);
}
package com.senla.training.accounting_system.repository;

import com.senla.training.accounting_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsById(Long bookId);

    List<Book> findAllByCategoryId(Long categoryId);
}
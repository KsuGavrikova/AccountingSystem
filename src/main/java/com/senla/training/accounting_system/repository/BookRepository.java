package com.senla.training.accountingSystem.repository;

import com.senla.training.accountingSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsById(Long bookId);

    List<Book> findAllByCategoryId(Long categoryId);
}
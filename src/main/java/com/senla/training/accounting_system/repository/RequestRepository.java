package com.senla.training.accounting_system.repository;

import com.senla.training.accounting_system.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    Page<Request> findAll(Pageable pageable);
}
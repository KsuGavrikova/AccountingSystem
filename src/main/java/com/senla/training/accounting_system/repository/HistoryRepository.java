package com.senla.training.accountingSystem.repository;

import com.senla.training.accountingSystem.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}
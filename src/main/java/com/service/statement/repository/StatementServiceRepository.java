package com.service.statement.repository;

import com.service.statement.entity.Statement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatementServiceRepository extends JpaRepository<Statement, Long> {
    List<Statement> findByCardId(Long cardId);
}

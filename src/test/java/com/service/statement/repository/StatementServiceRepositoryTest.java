package com.service.statement.repository;

import com.service.statement.entity.Statement;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static com.service.statement.StatementServiceTestConfiguration.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class StatementServiceRepositoryTest {

    @Autowired
    StatementServiceRepository statementServiceRepository;

    static Statement savedStatement;

    @Test
    void testAddNewStatement() {
        savedStatement = statementServiceRepository.save(STATEMENT);
        assertNotNull(savedStatement);
    }

    @Test
    void testFindByStatementId() {
        Optional<Statement> newCard = statementServiceRepository.findById(savedStatement.getStatementId());
        assertNotNull(newCard);
    }

    @Test
    void testFindByCardId() {
        Optional<Statement> newCard = statementServiceRepository.findById(savedStatement.getCardId());
        assertNotNull(newCard);
    }

    @Test
    void testFindAllCards() {
        List<Statement> newCard = statementServiceRepository.findAll();
        assertTrue(newCard.size() > 0);
    }

}

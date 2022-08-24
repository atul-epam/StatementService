package com.service.statement.service;

import com.service.statement.entity.Statement;
import com.service.statement.exception.StatementNotFoundException;
import com.service.statement.repository.StatementServiceRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static com.service.statement.StatementServiceTestConfiguration.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StatementServiceTest {

    @Autowired
    private StatementService statementService;

    @MockBean
    private StatementServiceRepository statementRepository;

    @Test
    void testNewStatementAddedSuccessfully() {
        Mockito.when(statementRepository.save(STATEMENT))
                .thenReturn(STATEMENT);
        Statement newStatement = statementService.addNewStatement(STATEMENT);
        assertEquals(newStatement,
                    STATEMENT);
    }

    @Test
    void testStatementRetrievedSuccessfullyWithStatementId() {
        Mockito.when(statementRepository.findById(VALID_ID))
                .thenReturn(Optional.of(STATEMENT));
        Statement newStatement = statementService.getStatement(VALID_ID);
        assertEquals(newStatement,
                    STATEMENT);
    }

    @Test
    void testStatementRetrievedSuccessfullyWithCardId() {
        Mockito.when(statementRepository.findByCardId(VALID_ID))
                .thenReturn(getStatementList());
        List<Statement> newStatementList = statementService.getStatements(VALID_ID);
        assertEquals(newStatementList,
                    getStatementList());
    }

    @Test
    void testStatementDetailsNotAvailable() {
        Mockito.when(statementRepository.findById(INVALID_ID))
                .thenThrow(new StatementNotFoundException("Statement with Id 2 not found."));
        assertThrows(StatementNotFoundException.class,
                        ()-> statementService.getStatement(VALID_ID));
    }

    @Test
    void testAllStatementDetailsRetrievedSuccessfully() {

        Mockito.when(statementRepository.findAll())
                .thenReturn(getStatementList());
        List<Statement> statementList = statementService.getStatements();
        assertEquals(statementList,
                    getStatementList());
    }
}

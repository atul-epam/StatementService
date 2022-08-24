package com.service.statement.controller;

import com.service.statement.entity.Statement;
import com.service.statement.exception.InvalidCardIdException;
import com.service.statement.exception.InvalidStatementIdException;
import com.service.statement.service.StatementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StatementServiceController {

    final StatementService statementService;

    public StatementServiceController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping("/statements")
    public ResponseEntity<Object> getStatements() {
        return ResponseEntity.ok(statementService.getStatements());
    }

    @GetMapping("/statement/{statementId}")
    public ResponseEntity<Statement> getStatement(@PathVariable Long statementId) {
        if(statementId <= 0) {
            throw new InvalidStatementIdException("Invalid statement Id.");
        }
        return ResponseEntity.ok(statementService.getStatement(statementId));
    }

    @GetMapping("/statements/{cardId}")
    public ResponseEntity<Object> getStatements(@PathVariable Long cardId) {
        if(cardId <= 0) {
            throw new InvalidCardIdException("Invalid card Id.");
        }
        List<Statement> statementList = statementService.getStatements(cardId);
        return ResponseEntity.ok(statementList);
    }

    @PostMapping("/new-statement")
    public ResponseEntity<Statement> createStatement(@Valid @RequestBody Statement statement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(statementService.addNewStatement(statement));
    }
}

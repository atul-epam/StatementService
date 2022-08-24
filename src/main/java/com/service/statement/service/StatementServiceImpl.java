package com.service.statement.service;

import com.service.statement.entity.Statement;
import com.service.statement.exception.StatementNotFoundException;
import com.service.statement.repository.StatementServiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatementServiceImpl implements StatementService {
    final StatementServiceRepository statementRepository;

    public StatementServiceImpl(StatementServiceRepository statementRepository) {
        this.statementRepository = statementRepository;
    }

    public List<Statement> getStatements() {
        List<Statement> statementList = statementRepository.findAll();
        if(statementList.isEmpty()) {
            throw new StatementNotFoundException("No statement found in the database");
        }
        return statementList;
    }

    public Statement getStatement(Long statementId) {
        Optional<Statement> statement = statementRepository.findById(statementId);
        return statement.orElseThrow(() -> new StatementNotFoundException("Card with Id " + statementId + " not found."));
    }

    @Override
    public Statement addNewStatement(Statement statement) {
        return statementRepository.save(statement);
    }

    public List<Statement> getStatements(Long cardId) {
        List<Statement> statementList = statementRepository.findByCardId(cardId);

        if(statementList == null || statementList.isEmpty()) {
            throw new StatementNotFoundException("No statement found for Card id " + cardId);
        }
        return statementRepository.findByCardId(cardId);
    }
}
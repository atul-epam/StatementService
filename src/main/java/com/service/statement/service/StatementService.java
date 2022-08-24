package com.service.statement.service;

import com.service.statement.entity.Statement;

import java.util.List;

public interface StatementService {

    List<Statement> getStatements();
    List<Statement> getStatements(Long cardId);
    Statement getStatement(Long statementId);
    Statement addNewStatement(Statement statement);

}

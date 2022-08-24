package com.service.statement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.statement.entity.Statement;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StatementServiceTestConfiguration {

    public static final Long VALID_ID = 1L;
    public static final Long INVALID_ID = 2L;
    public static final Long AMOUNT = 1000L;
    public static final String STATEMENT_DATE = "2022-08-23";
    public static final Statement STATEMENT = new Statement(VALID_ID,
                                                            VALID_ID,
                                                            AMOUNT,
                                                            LocalDate.parse(STATEMENT_DATE));

    public static final Statement INVALID_STATEMENT = new Statement(INVALID_ID,
                                                                    VALID_ID,
                                                                    AMOUNT,
                                                                    LocalDate.parse(STATEMENT_DATE));
    public static List<Statement> getStatementList() {
        List<Statement> statementList = new ArrayList<>();
        statementList.add(STATEMENT);
        return statementList;
    }

    public static String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return objectMapper.writeValueAsString(obj);
    }
}

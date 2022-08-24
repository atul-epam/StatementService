package com.service.statement.controller;

import com.service.statement.exception.InvalidCardIdException;
import com.service.statement.exception.InvalidStatementIdException;
import com.service.statement.service.StatementService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.service.statement.StatementServiceTestConfiguration.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class StatementServiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StatementService statementService;

    @Test
    void testNewStatementIsSaved() throws Exception {
        Mockito.when(statementService.addNewStatement(ArgumentMatchers.any())).thenReturn(STATEMENT);

        mockMvc.perform(post("/new-statement")
                           .contentType(MediaType.APPLICATION_JSON_VALUE)
                           .content(mapToJson(STATEMENT)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statementId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.cardId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.amount", Matchers.equalTo(1000.0)))
                .andExpect(jsonPath("$.statementDate", Matchers.equalTo(STATEMENT_DATE)));
    }

    @Test
    void testNewStatementWithIncorrectDetails() throws Exception {
        Mockito.when(statementService.addNewStatement(ArgumentMatchers.any())).thenReturn(INVALID_STATEMENT);

        mockMvc.perform(post("/new-statement")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testToGetAllStatements() throws Exception {
        Mockito.when(statementService.getStatements()).thenReturn(getStatementList());

        mockMvc.perform(get("/statements"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].statementId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].cardId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].amount", Matchers.equalTo(1000.0)))
                .andExpect(jsonPath("$[0].statementDate", Matchers.equalTo(STATEMENT_DATE)));
    }

    @Test
    void testToGetStatementWithStatementId() throws Exception {
        Mockito.when(statementService.getStatement(ArgumentMatchers.any())).thenReturn(STATEMENT);

        mockMvc.perform(get("/statement/1/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statementId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.cardId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.amount", Matchers.equalTo(1000.0)))
                .andExpect(jsonPath("$.statementDate", Matchers.equalTo(STATEMENT_DATE)));
    }

    @Test
    void testToGetStatementWithInvalidStatementId() throws Exception {
        Mockito.when(statementService.getStatement(INVALID_ID)).thenThrow(new InvalidStatementIdException("Invalid statement Id."));

        mockMvc.perform(get("/statement/2/"))
                .andExpect(status().isNotAcceptable());
    }

    @Test
    void testToGetStatementWithCardId() throws Exception {
        Mockito.when(statementService.getStatements(ArgumentMatchers.any())).thenReturn(getStatementList());

        mockMvc.perform(get("/statements/1/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].statementId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].cardId", Matchers.equalTo(1)))
                .andExpect(jsonPath("$[0].amount", Matchers.equalTo(1000.0)))
                .andExpect(jsonPath("$[0].statementDate", Matchers.equalTo(STATEMENT_DATE)));
    }

    @Test
    void testToGetStatementWithInvalidCardId() throws Exception {
        Mockito.when(statementService.getStatements(INVALID_ID)).thenThrow(new InvalidCardIdException("Invalid card Id."));

        mockMvc.perform(get("/statements/2/"))
                .andExpect(status().isNotAcceptable());
    }
}

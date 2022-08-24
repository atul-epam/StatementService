package com.service.statement.exception;

public class InvalidStatementIdException extends RuntimeException {
    public InvalidStatementIdException(String msg)
    {
        super(msg);
    }
}

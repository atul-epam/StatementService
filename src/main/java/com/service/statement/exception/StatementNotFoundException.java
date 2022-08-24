package com.service.statement.exception;

public class StatementNotFoundException extends RuntimeException {

    public StatementNotFoundException(String msg)
    {
        super(msg);
    }
}

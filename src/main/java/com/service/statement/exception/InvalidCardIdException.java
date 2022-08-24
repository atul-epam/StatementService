package com.service.statement.exception;

public class InvalidCardIdException extends RuntimeException {

    public InvalidCardIdException(String msg)
    {
        super(msg);
    }
}

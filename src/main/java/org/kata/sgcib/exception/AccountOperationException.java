package org.kata.sgcib.exception;

public class AccountOperationException extends RuntimeException{
    public AccountOperationException(String message) {
        super(message);
    }
}

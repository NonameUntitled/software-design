package ru.itmo.calculator.exception;

public class InvalidExpressionException extends RuntimeException {
    public InvalidExpressionException() {
        super("Expression error: Incorrect expression is given");
    }
}

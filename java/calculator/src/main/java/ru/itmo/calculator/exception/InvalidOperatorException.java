package ru.itmo.calculator.exception;

public class InvalidOperatorException extends RuntimeException {
    public InvalidOperatorException(final char operator) {
        super(String.format("Expression error: Expected an operand, but got '%c'", operator));
    }
}

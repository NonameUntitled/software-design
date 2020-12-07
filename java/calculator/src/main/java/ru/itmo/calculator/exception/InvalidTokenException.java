package ru.itmo.calculator.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(final char c) {
        super(String.format("Expression error: Gon an unexpected token '%c'", c));
    }
}

package ru.itmo.calculator.exception;

public class InvalidBracketsMatchingException extends RuntimeException {
    public InvalidBracketsMatchingException() {
        super("Expression error: Open and close brackets mismatching");
    }
}

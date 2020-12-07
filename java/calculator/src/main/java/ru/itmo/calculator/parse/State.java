package ru.itmo.calculator.parse;

import ru.itmo.calculator.exception.InvalidTokenException;

public interface State {
    void process(char c, Tokenizer tokenizer);

    default State nextState(final char c) {
        if (Tokenizer.getBrackets().contains(c)) {
            return new BracketState(c);
        }
        if (Tokenizer.getOperators().contains(c)) {
            return new OperatorState(c);
        }
        if (Character.isDigit(c)) {
            return new NumberState(c);
        }
        if (Character.isWhitespace(c)) {
            return new NoneState();
        }
        throw new InvalidTokenException(c);
    }
}

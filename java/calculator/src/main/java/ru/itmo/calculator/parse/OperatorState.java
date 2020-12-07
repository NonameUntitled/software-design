package ru.itmo.calculator.parse;

import ru.itmo.calculator.token.Operation;

public class OperatorState implements State {
    private final char operator;

    public OperatorState(final char c) {
        this.operator = c;
    }

    @Override
    public void process(final char c, final Tokenizer tokenizer) {
        tokenizer.getTokens().add(new Operation(operator));
        tokenizer.setState(nextState(c));
    }
}

package ru.itmo.calculator.parse;

import ru.itmo.calculator.token.Number;

public class NumberState implements State {
    private final StringBuilder numberBuilder = new StringBuilder();

    public NumberState(final char c) {
        numberBuilder.append(c);
    }

    @Override
    public void process(final char c, final Tokenizer tokenizer) {
        if (Character.isDigit(c)) {
            numberBuilder.append(c);
        } else {
            tokenizer.getTokens().add(new Number(Long.parseLong(numberBuilder.toString())));
            tokenizer.setState(nextState(c));
        }
    }
}

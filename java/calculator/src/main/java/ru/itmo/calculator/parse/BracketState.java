package ru.itmo.calculator.parse;

import ru.itmo.calculator.token.Bracket;

public class BracketState implements State {
    private final char bracket;

    public BracketState(final char c) {
        this.bracket = c;
    }

    @Override
    public void process(final char c, final Tokenizer tokenizer) {
        tokenizer.getTokens().add(new Bracket(bracket));
        tokenizer.setState(nextState(c));
    }
}

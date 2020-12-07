package ru.itmo.calculator.parse;

import ru.itmo.calculator.token.Bracket;

public class BracketState implements State {
    private final char brace;

    public BracketState(final char c) {
        this.brace = c;
    }

    @Override
    public void process(final char c, final Tokenizer tokenizer) {
        tokenizer.getTokens().add(new Bracket(brace));
        tokenizer.setState(nextState(c));
    }
}

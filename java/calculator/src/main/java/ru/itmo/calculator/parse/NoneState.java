package ru.itmo.calculator.parse;

public class NoneState implements State {
    @Override
    public void process(final char c, final Tokenizer tokenizer) {
        tokenizer.setState(nextState(c));
    }
}

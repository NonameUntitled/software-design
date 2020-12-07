package ru.itmo.calculator.token;

import ru.itmo.calculator.visitor.TokenVisitor;

public class Bracket implements Token {
    private final char brace;

    public Bracket(final char c) {
        this.brace = c;
    }

    public void visit(final TokenVisitor visitor) {
        visitor.visit(this);
    }

    public char getBrace() {
        return brace;
    }
}

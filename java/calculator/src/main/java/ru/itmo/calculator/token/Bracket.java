package ru.itmo.calculator.token;

import ru.itmo.calculator.visitor.TokenVisitor;

public class Bracket implements Token {
    private final char bracket;

    public Bracket(final char c) {
        this.bracket = c;
    }

    public void visit(final TokenVisitor visitor) {
        visitor.visit(this);
    }

    public char getBracket() {
        return bracket;
    }
}

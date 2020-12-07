package ru.itmo.calculator.token;

import ru.itmo.calculator.visitor.TokenVisitor;

public class Number implements Token {
    private final Long number;

    public Number(final Long number) {
        this.number = number;
    }

    @Override
    public void visit(final TokenVisitor visitor) {
        visitor.visit(this);
    }

    public Long getNumber() {
        return number;
    }
}

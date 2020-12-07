package ru.itmo.calculator.visitor;

import ru.itmo.calculator.token.Bracket;
import ru.itmo.calculator.token.Number;
import ru.itmo.calculator.token.Operation;

public class PrintVisitor implements TokenVisitor {
    private final StringBuilder builder = new StringBuilder();

    @Override
    public void visit(final Number token) {
        builder.append(token.getNumber());
        builder.append(' ');
    }

    @Override
    public void visit(final Bracket token) {
        builder.append(token.getBrace());
        builder.append(' ');
    }

    @Override
    public void visit(final Operation token) {
        builder.append(token.getOperator());
        builder.append(' ');
    }

    public String getResult() {
        return builder.toString();
    }
}

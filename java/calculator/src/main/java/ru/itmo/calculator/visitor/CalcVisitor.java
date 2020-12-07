package ru.itmo.calculator.visitor;

import ru.itmo.calculator.exception.InvalidExpressionException;
import ru.itmo.calculator.exception.InvalidOperatorException;
import ru.itmo.calculator.token.Bracket;
import ru.itmo.calculator.token.Number;
import ru.itmo.calculator.token.Operation;
import ru.itmo.calculator.token.Token;

import java.util.ArrayDeque;
import java.util.Deque;

public class CalcVisitor implements TokenVisitor {
    private final Deque<Token> tokens = new ArrayDeque<>();

    @Override
    public void visit(final Number token) {
        tokens.push(token);
    }

    @Override
    public void visit(final Bracket token) {
        // no action
    }

    @Override
    public void visit(final Operation token) {
        if (tokens.size() < 2) {
            throw new InvalidOperatorException(token.getOperator());
        }
        final var right = tokens.pop();
        final var left = tokens.pop();
        if (!(left instanceof Number)) {
            throw new InvalidOperatorException(((Operation) left).getOperator());
        }
        if (!(right instanceof Number)) {
            throw new InvalidOperatorException(((Operation) right).getOperator());
        }
        final var l = ((Number) left).getNumber();
        final var r = ((Number) right).getNumber();
        switch (token.getOperator()) {
            case '+':
                tokens.push(new Number(l + r));
                break;
            case '-':
                tokens.push(new Number(l - r));
                break;
            case '*':
                tokens.push(new Number(l * r));
                break;
            case '/':
                tokens.push(new Number(l / r));
                break;
        }
    }

    public Long getResult() {
        if (tokens.size() != 1) {
            throw new InvalidExpressionException();
        }
        return ((Number) tokens.peek()).getNumber();
    }
}

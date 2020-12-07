package ru.itmo.calculator.token;

import ru.itmo.calculator.visitor.TokenVisitor;

public interface Token {
    void visit(TokenVisitor visitor);
}

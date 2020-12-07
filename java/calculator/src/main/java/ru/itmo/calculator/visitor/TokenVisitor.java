package ru.itmo.calculator.visitor;

import ru.itmo.calculator.token.Bracket;
import ru.itmo.calculator.token.Number;
import ru.itmo.calculator.token.Operation;

public interface TokenVisitor {
    void visit(Number token);
    void visit(Bracket token);
    void visit(Operation token);
}
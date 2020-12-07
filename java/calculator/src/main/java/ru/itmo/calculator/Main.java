package ru.itmo.calculator;

import ru.itmo.calculator.parse.Tokenizer;
import ru.itmo.calculator.visitor.CalcVisitor;
import ru.itmo.calculator.visitor.ParserVisitor;
import ru.itmo.calculator.visitor.PrintVisitor;

public class Main {
    public static void main(final String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main <expression>");
            return;
        }

        final var tokenizer = new Tokenizer();

        final var parserVisitor = new ParserVisitor();
        final var printVisitor = new PrintVisitor();
        final var calcVisitor = new CalcVisitor();

        final var expression = args[0];

        for (final var c : expression.toCharArray()) {
            tokenizer.handle(c);
        }

        final var tokens = tokenizer.getResult();

        for (final var t : tokens) {
            t.visit(parserVisitor);
        }

        final var processedTokens = parserVisitor.getResult();

        for (final var t : processedTokens) {
            t.visit(printVisitor);
        }

        System.out.println(printVisitor.getResult());

        for (final var t : processedTokens) {
            t.visit(calcVisitor);
        }

        System.out.println(calcVisitor.getResult());
    }
}

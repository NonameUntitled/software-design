package ru.itmo.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import ru.itmo.calculator.exception.InvalidExpressionException;
import ru.itmo.calculator.exception.InvalidOperatorException;
import ru.itmo.calculator.exception.InvalidBracketsMatchingException;
import ru.itmo.calculator.exception.InvalidTokenException;
import ru.itmo.calculator.parse.Tokenizer;
import ru.itmo.calculator.visitor.CalcVisitor;
import ru.itmo.calculator.visitor.ParserVisitor;
import ru.itmo.calculator.visitor.PrintVisitor;

public class CalculatorTest {

    public Long launchTest(final String input) {
        final Tokenizer tokenizer = new Tokenizer();
        for (final var c : input.toCharArray()) {
            tokenizer.handle(c);
        }

        final var tokens = tokenizer.getResult();
        final var parserVisitor = new ParserVisitor();
        for (final var t : tokens) {
            t.visit(parserVisitor);
        }

        final var rpnTokens = parserVisitor.getResult();
        final var printVisitor = new PrintVisitor();
        for (final var t : rpnTokens) {
            t.visit(printVisitor);
        }

        final var calcVisitor = new CalcVisitor();
        for (final var t : rpnTokens) {
            t.visit(calcVisitor);
        }

        return calcVisitor.getResult();
    }

    @Test
    public void correctTests() {
        assertEquals(0L, launchTest("0"));
        assertEquals(4L, launchTest("2 +2"));
        assertEquals(4L, launchTest("((((((((((((((4))))))))))))))"));
        assertEquals(8L, launchTest("3 * 2 + 1 + 1"));
        assertEquals(23L, launchTest(" 21 - 5 * 5 / 2 + ( 44 / 4 - 12 / 3 ) * 2 "));
        assertEquals(0L, launchTest("(1123*2534*3343*0+5+6+7-8-9/2/3)*0"));
        assertEquals(6L, launchTest("2 *     3"));
        assertEquals(0L, launchTest("0 / 5"));
    }

    @Test
    public void invalidExpressionTests() {
        assertThrows(InvalidExpressionException.class, () -> launchTest(""));
        assertThrows(InvalidExpressionException.class, () -> launchTest(" "));
        assertThrows(InvalidExpressionException.class, () -> launchTest("0 0"));
    }

    @Test
    public void invalidOperatorTests() {
        assertThrows(InvalidOperatorException.class, () -> launchTest("4 --"));
        assertThrows(InvalidOperatorException.class, () -> launchTest("10 *(-2)"));
        assertThrows(InvalidOperatorException.class, () -> launchTest("-3"));
    }

    @Test
    public void invalidBracketsMatchingTest() {
        assertThrows(InvalidBracketsMatchingException.class, () -> launchTest("(3 + 4) * ((3 + 2)))"));
        assertThrows(InvalidBracketsMatchingException.class, () -> launchTest("3 + 4)"));
        assertThrows(InvalidBracketsMatchingException.class, () -> launchTest("1))"));
    }

    @Test
    public void testCalculatorInvalidToken() {
        assertThrows(InvalidTokenException.class, () -> launchTest("botinok"));
        assertThrows(InvalidTokenException.class, () -> launchTest("2.2"));
        assertThrows(InvalidTokenException.class, () -> launchTest("four"));
        assertThrows(InvalidTokenException.class, () -> launchTest("S"));
        assertThrows(InvalidTokenException.class, () -> launchTest("@"));
    }
}

package ru.itmo.calculator.parse;

import ru.itmo.calculator.token.Token;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tokenizer {
    private static final Set<Character> operators = new HashSet<>();
    private static final Set<Character> brackets = new HashSet<>();

    static {
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');
        brackets.add('(');
        brackets.add(')');
    }

    private final List<Token> tokens = new ArrayList<>();

    private State state = new NoneState();

    public void handle(final char c) {
        state.process(c, this);
    }

    public List<Token> getResult() {
        if (!(state instanceof NoneState)) {
            state.process(' ', this);
        }
        return tokens;
    }

    public void setState(final State state) {
        this.state = state;
    }

    public static Set<Character> getOperators() {
        return operators;
    }

    public static Set<Character> getBrackets() {
        return brackets;
    }

    protected List<Token> getTokens() {
        return tokens;
    }
}

package com.tfr.math.expression;

import java.util.Set;
import java.util.Stack;

public abstract class ExpressionEvaluator<I extends Number> {
    Set<Character> operators = Set.of('+', '-', '*', '/');

    abstract I evaluate(String expression);

    boolean isOperator(char token) {
        return operators.contains(token);
    }

    boolean hasPrecedence(char operator1, char operator2) {
        if (operator2 == '(' || operator2 == ')') {
            return false;
        }
        if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-')) {
            return false;
        }
        return true;
    }

    boolean isNumber(char token) {
        return token >= '0' && token <= '9';
    }

    boolean isDecimal(char token) {
        return token == '.';
    }

    void printStacks(Stack<I> values, Stack<Character> operators) {
        System.out.println("vals: " + values);
        System.out.println("ops : " + operators);
    }
}

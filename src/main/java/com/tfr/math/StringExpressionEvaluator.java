package com.tfr.math;

import java.util.Set;
import java.util.Stack;

public class StringExpressionEvaluator {

    private static final Set<Character> operators = Set.of('+', '-', '*', '/');

    public static double evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        Stack<Double> values = new Stack<>();
        Stack<Character> operations = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') {
                continue;
            }

            if (isNumber(tokens[i])) {
                StringBuilder sb = new StringBuilder();

                while (i < tokens.length && isNumber(tokens[i])) {
                    sb.append(tokens[i++]);
                }

                values.push(Double.parseDouble(sb.toString()));
                i--;
            } else if (tokens[i] == '(') {
                operations.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (operations.peek() != '(') {
                    double result = applyOperation(operations.pop(), values.pop(), values.pop());
                    values.push(result);
                }
                operations.pop();
            } else if (isOperator(tokens[i])) {
                while (!operations.isEmpty() && hasPrecedence(tokens[i], operations.peek())) {
                    double result = applyOperation(operations.pop(), values.pop(), values.pop());
                    values.push(result);
                }
                operations.push(tokens[i]);
            }
        }

        while (!operations.isEmpty()) {
            double result = applyOperation(operations.pop(), values.pop(), values.pop());
            values.push(result);
        }

        return values.pop();
    }

    private static boolean hasPrecedence(char operator1, char operator2) {
        if (operator2 == '(' || operator2 == ')') {
            return false;
        }
        if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-')) {
            return false;
        }
        return true;
    }

    private static boolean isNumber(char token) {
        boolean isNumber = token >= '0' && token <= '9';
        boolean isDecimal = token == '.';

        return isNumber || isDecimal;
    }

    private static boolean isOperator(char token) {
        return operators.contains(token);
    }

    private static double applyOperation(char op, double b, double a) {
        switch (op) {
            case '+' -> {
                return a + b;
            }
            case '-' -> {
                return a - b;
            }
            case '*' -> {
                return a * b;
            }
            case '/' -> {
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
            }
        }

        return 0;
    }

}

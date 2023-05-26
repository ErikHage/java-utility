package com.tfr.math.expression;

import java.util.Set;
import java.util.Stack;

public class SimpleStringExpressionEvaluator {

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
                printStacks(values, operations);
                i--;
            } else if (isOperator(tokens[i])) {
                while (!operations.isEmpty() && hasPrecedence(tokens[i], operations.peek())) {
                    double result = applyOperation(operations.pop(), values.pop(), values.pop());
                    values.push(result);
                    printStacks(values, operations);
                }
                operations.push(tokens[i]);
                printStacks(values, operations);
            }
        }

        while (!operations.isEmpty()) {
            double result = applyOperation(operations.pop(), values.pop(), values.pop());
            values.push(result);
            printStacks(values, operations);
        }

        return values.pop();
    }

    private static boolean hasPrecedence(char operator1, char operator2) {
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
            case '+' -> { return a + b; }
            case '-' -> { return a - b; }
            case '*' -> { return a * b; }
            case '/' -> {
                if (b == 0) {
                    throw new UnsupportedOperationException("Cannot divide by zero");
                }
                return a / b;
            }
        }

        return 0;
    }

    private static void printStacks(Stack<Double> values, Stack<Character> operators) {
        System.out.println("vals: " + values);
        System.out.println("ops : " + operators);
    }

}

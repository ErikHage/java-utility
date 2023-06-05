package com.tfr.math.expression;

import java.util.Stack;

public class StringExpressionEvaluator extends ExpressionEvaluator<Double> {

    public Double evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        Stack<Double> values = new Stack<>();
        Stack<Character> operations = new Stack<>();

        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == ' ') {
                continue;
            }

            if (isNumber(tokens[i]) || isDecimal(tokens[i])) {
                StringBuilder sb = new StringBuilder();

                while (i < tokens.length && isNumber(tokens[i])) {
                    sb.append(tokens[i++]);
                }

                values.push(Double.parseDouble(sb.toString()));
                printStacks(values, operations);
                i--;
            } else if (tokens[i] == '(') {
                operations.push(tokens[i]);
            } else if (tokens[i] == ')') {
                while (operations.peek() != '(') {
                    double result = applyOperation(operations.pop(), values.pop(), values.pop());
                    values.push(result);
                    printStacks(values, operations);
                }
                operations.pop();
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

    private double applyOperation(char op, double b, double a) {
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
            default -> throw new UnsupportedOperationException("Invalid operation: " + op);
        }
    }
}

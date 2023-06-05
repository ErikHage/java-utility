package com.tfr.math.expression;

import java.util.Stack;

public class SimplerStringExpressionEvaluator extends ExpressionEvaluator<Integer> {

    public Integer evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        Stack<Integer> values = new Stack<>();
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
                values.push(Integer.parseInt(sb.toString()));
                printStacks(values, operations);
                i--;
            } else if (isOperator(tokens[i])) {
                while (!operations.isEmpty() && hasPrecedence(tokens[i], operations.peek())) {
                    int result = applyOperation(operations.pop(), values.pop(), values.pop());
                    values.push(result);
                    printStacks(values, operations);
                }
                operations.push(tokens[i]);
                printStacks(values, operations);
            }
        }

        while (!operations.isEmpty()) {
            int result = applyOperation(operations.pop(), values.pop(), values.pop());
            values.push(result);
            printStacks(values, operations);
        }

        return values.pop();
    }

    private int applyOperation(char op, int b, int a) {
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
            default -> throw new UnsupportedOperationException("Invalid operation: " + op);        }
    }
}

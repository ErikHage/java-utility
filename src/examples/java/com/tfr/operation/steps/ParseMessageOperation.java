package com.tfr.operation.steps;

import com.tfr.operation.exception.OperationException;
import com.tfr.operation.models.ExampleResult;
import com.tfr.operation.operation.Operation;

public class ParseMessageOperation implements Operation<String, ExampleResult> {
    @Override
    public String getName() {
        return "Parse Message Operation";
    }

    @Override
    public ExampleResult execute(String input) throws OperationException {
        String[] lines = input.split("\n");

        String sender = lines[1].substring(7);
        StringBuilder content = new StringBuilder();

        for (int i = 2; i < lines.length-1; i++) {
            content.append(lines[i]);
            content.append("\n");
        }

        return new ExampleResult(sender, content.toString());
    }
}

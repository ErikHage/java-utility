package com.tfr.operation.steps;

import com.tfr.operation.exception.ValidationException;
import com.tfr.operation.validation.Validation;

public class IsValidMessageFormatValidation implements Validation<String> {
    @Override
    public String getName() {
        return "Is Valid Message Validation";
    }

    @Override
    public void validate(String input) throws ValidationException {
        String[] lines = input.split("\n");

        boolean hasHeader = lines[0].contains("::BEGIN MESSAGE::");
        boolean hasFooter = lines[lines.length-1].contains("::END MESSAGE::");
        boolean hasSender = lines[1].contains("sender:");

        if (!hasHeader) {
            throw new ValidationException("Message missing a header");
        }

        if (!hasFooter) {
            throw new ValidationException("Message missing a footer");
        }

        if (!hasSender) {
            throw new ValidationException("Message missing a sender");
        }

        if (lines.length < 4) {
            throw new ValidationException("Message has no content");
        }

    }
}

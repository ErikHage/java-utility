package com.tfr.monad.model;

public class FailedValidation extends ValidationResult {

    private String reason;

    public FailedValidation(String validationName, String reason) {
        super(validationName);
        this.reason = reason;
    }

    @Override
    public boolean isSuccessful() {
        return false;
    }

    @Override
    public String getMessage() {
        return String.format("Validation '%s' failed with reason: %s", validationName, reason);
    }
}

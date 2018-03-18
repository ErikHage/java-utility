package com.tfr.monad.model;

public abstract class ValidationResult implements Result {

    protected String validationName;

    ValidationResult(String validationName) {
        this.validationName = validationName;
    }

    public abstract boolean isSuccessful();
    public abstract String getMessage();
}

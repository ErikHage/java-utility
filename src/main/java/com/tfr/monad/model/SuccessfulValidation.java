package com.tfr.monad.model;

public class SuccessfulValidation extends ValidationResult {

    public SuccessfulValidation(String validationName) {
        super(validationName);
    }

    @Override
    public boolean isSuccessful() {
        return true;
    }

    @Override
    public String getMessage() {
        return "Validation '" + this.validationName + "' was successful";
    }

}

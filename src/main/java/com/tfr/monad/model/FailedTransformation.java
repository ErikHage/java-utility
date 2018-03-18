package com.tfr.monad.model;

public class FailedTransformation extends TransformationResult {

    private String reason;

    public FailedTransformation(String name, String reason) {
        super(name);
        this.reason = reason;
    }

    @Override
    public boolean isSuccessful() {
        return false;
    }

    @Override
    public String getMessage() {
        return String.format("Transformation '%s' failed with reason: %s", transformationName, reason);
    }
}

package com.tfr.monad.model;

public class SuccessfulTransformation extends TransformationResult {

    public SuccessfulTransformation(String name) {
        super(name);
    }

    @Override
    public boolean isSuccessful() {
        return true;
    }

    @Override
    public String getMessage() {
        return String.format("Transformation '%s' completed successfully", transformationName);
    }
}

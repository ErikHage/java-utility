package com.tfr.monad.model;

public abstract class TransformationResult implements Result {

    protected String transformationName;

    public TransformationResult(String transformationName) {
        this.transformationName = transformationName;
    }

    public abstract boolean isSuccessful();
    public abstract String getMessage();
}

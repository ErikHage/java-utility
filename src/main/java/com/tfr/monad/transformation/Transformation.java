package com.tfr.monad.transformation;

@FunctionalInterface
public interface Transformation<IN, OUT> {

    default String getName() {
        return this.getClass().getName();
    }

    OUT execute(IN input) throws TransformationException;
}

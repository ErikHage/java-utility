package com.tfr.operation;

public interface OperationChain<I> {

    <O> OperationChain<O> transform(Operation<I,O> operation);
    OperationChain<I> validate(Validation<I> validation);
    I getState();
}

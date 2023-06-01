package com.tfr.operation;

import com.tfr.operation.exception.OperationChainException;

public class ShortCircuitOperationChain<I> implements OperationChain<I> {
    private final OperationChainException cause;

    ShortCircuitOperationChain(OperationChainException cause) {
        this.cause = cause;
    }

    @Override
    public <O> OperationChain<O> transform(Operation<I, O> operation) {
        // audit here
        return new ShortCircuitOperationChain<>(cause);
    }

    @Override
    public OperationChain<I> validate(Validation<I> validation) {
        // audit here
        return this;
    }

    @Override
    public I getState() {
        return null;
    }

    public OperationChainException getFailureCause() {
        return cause;
    }
}

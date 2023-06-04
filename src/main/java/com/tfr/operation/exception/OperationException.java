package com.tfr.operation.exception;

public class OperationException extends OperationChainException {

    public OperationException(String message) {
        super(message);
    }

    public OperationException(Throwable cause) {
        super(cause);
    }
}

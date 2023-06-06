package com.tfr.operation.exception;

public class OperationChainException extends RuntimeException {

    public OperationChainException(String message) {
        super(message);
    }

    public OperationChainException(Throwable cause) {
        super(cause);
    }

    public OperationChainException(String message, Throwable cause) {
        super(message, cause);
    }
}

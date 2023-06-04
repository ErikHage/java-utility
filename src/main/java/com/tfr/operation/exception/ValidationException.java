package com.tfr.operation.exception;

public class ValidationException extends OperationChainException {

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }
}

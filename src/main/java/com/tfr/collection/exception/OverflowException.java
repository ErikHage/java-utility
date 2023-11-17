package com.tfr.collection.exception;

public class OverflowException extends RuntimeException {

    /**
     * Thrown when a stack overflow occurs because an application recurses too deeply.
     * @param message String
     */
    public OverflowException(String message) {
        super(message);
    }
}

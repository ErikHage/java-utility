package com.tfr.operation.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationExceptionTest {

    @Test
    public void testConstructors() {
        OperationChainException ex1 = new ValidationException("message");
        assertTrue(ex1 instanceof ValidationException);

        OperationChainException ex2 = new ValidationException(ex1);
        assertTrue(ex2 instanceof ValidationException);

        OperationChainException ex3 = new ValidationException("message", ex2);
        assertTrue(ex3 instanceof ValidationException);
    }
}

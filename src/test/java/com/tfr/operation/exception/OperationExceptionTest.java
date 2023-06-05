package com.tfr.operation.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OperationExceptionTest {

    @Test
    public void testConstructors() {
        OperationChainException ex1 = new OperationException("message");
        assertTrue(ex1 instanceof OperationException);

        OperationChainException ex2 = new OperationException(ex1);
        assertTrue(ex2 instanceof OperationException);

        OperationChainException ex3 = new OperationException("message", ex2);
        assertTrue(ex3 instanceof OperationException);
    }
}

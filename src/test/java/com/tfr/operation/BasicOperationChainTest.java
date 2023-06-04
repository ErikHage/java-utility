package com.tfr.operation;

import com.tfr.operation.audit.AuditTrail;
import com.tfr.operation.exception.OperationChainException;
import com.tfr.operation.exception.OperationException;
import com.tfr.operation.exception.ValidationException;
import com.tfr.operation.operation.Operation;
import com.tfr.operation.validation.Validation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BasicOperationChainTest {

    @Mock
    private Operation<String, String> operation;
    @Mock
    private Validation<String> validation;

    private AutoCloseable autoCloseable;
    private OperationChainException testException;

    @BeforeEach
    public void setUp() throws OperationException, ValidationException {
        autoCloseable = MockitoAnnotations.openMocks(this);
        testException = new OperationException();

        when(operation.getName()).thenReturn("test-operation-name");
        when(operation.execute("first")).thenReturn("second");
        when(validation.getName()).thenReturn("test-validation-name");
    }

    @AfterEach
    public void cleanUp() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testTransform_GivenOperation_ReturnsNewOperationChain() throws OperationException {
        BasicOperationChain<String> opChain = BasicOperationChain.of("first");

        OperationChain<String> result = opChain.transform(operation);

        assertTrue(result instanceof BasicOperationChain<String>);
        assertNotSame(opChain, result);
        assertEquals("second", result.getState());

        verify(operation, times(1)).execute("first");
    }

    @Test
    public void testTransform_GivenValidation_ReturnsNewOperationChain() throws ValidationException {
        BasicOperationChain<String> opChain = BasicOperationChain.of("first");

        OperationChain<String> result = opChain.validate(validation);

        assertTrue(result instanceof BasicOperationChain<String>);
        assertSame(opChain, result);
        assertEquals("first", result.getState());

        verify(validation, times(1)).validate("first");
    }

    @Test
    public void testGetState_ReturnsNull() {
        BasicOperationChain<String> opChain = BasicOperationChain.of("first");

        assertEquals("first", opChain.getState());
    }
}

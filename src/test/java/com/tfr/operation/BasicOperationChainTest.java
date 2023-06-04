package com.tfr.operation;

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
    private OperationException testOperationException;
    private ValidationException testValidationException;

    @BeforeEach
    public void setUp() throws OperationException {
        autoCloseable = MockitoAnnotations.openMocks(this);
        testOperationException = new OperationException();
        testValidationException = new ValidationException();

        when(operation.getName()).thenReturn("test-operation-name");
        when(validation.getName()).thenReturn("test-validation-name");
    }

    @AfterEach
    public void cleanUp() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testTransform_GivenOperation_ReturnsNewOperationChain() throws OperationException {
        when(operation.execute("first")).thenReturn("second");

        BasicOperationChain<String> opChain = BasicOperationChain.of("first");

        OperationChain<String> result = opChain.transform(operation);

        assertTrue(result instanceof BasicOperationChain<String>);
        assertNotSame(opChain, result);
        assertEquals("second", result.getState());

        verify(operation, times(1)).execute("first");
    }

    @Test
    public void testTransform_GivenOperationThrowsException_ReturnsShortCircuitOperationChain() throws OperationException {
        when(operation.execute("first")).thenThrow(testOperationException);

        BasicOperationChain<String> opChain = BasicOperationChain.of("first");

        OperationChain<String> result = opChain.transform(operation);

        assertTrue(result instanceof ShortCircuitOperationChain<String>);

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
    public void testTransform_GivenValidationThrowsException_ReturnsShortCircuitOperationChain() throws ValidationException {
        doThrow(testValidationException).when(validation).validate("first");

        BasicOperationChain<String> opChain = BasicOperationChain.of("first");

        OperationChain<String> result = opChain.validate(validation);

        assertTrue(result instanceof ShortCircuitOperationChain<String>);

        verify(validation, times(1)).validate("first");
    }

    @Test
    public void testGetState_ReturnsNull() {
        BasicOperationChain<String> opChain = BasicOperationChain.of("first");

        assertEquals("first", opChain.getState());
    }

    @Test
    public void testGetAuditTrail_ReturnsAuditTrail() {
        BasicOperationChain<String> opChain = BasicOperationChain.of("first");

        OperationChain<String> result = opChain.validate(validation);

        assertNotNull(result.getAuditTrail());
        assertEquals(1, result.getAuditTrail().getAudits().size());
    }
}

package com.tfr.operation;

import com.tfr.operation.audit.AuditTrail;
import com.tfr.operation.exception.OperationChainException;
import com.tfr.operation.exception.OperationException;
import com.tfr.operation.operation.Operation;
import com.tfr.operation.validation.Validation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ShortCircuitOperationChainTest {

    @Mock
    private Operation<String, String> operation;
    @Mock
    private Validation<String> validation;
    @Mock
    private AuditTrail auditTrail;

    private AutoCloseable autoCloseable;
    private OperationChainException testException;

    @BeforeEach
    public void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        testException = new OperationException();

        when(operation.getName()).thenReturn("test-operation-name");
        when(validation.getName()).thenReturn("test-validation-name");
    }

    @AfterEach
    public void cleanUp() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void testTransform_GivenOperation_ReturnsShortCircuitOperationChain() {
        ShortCircuitOperationChain<String> opChain = new ShortCircuitOperationChain<>(testException, auditTrail);

        OperationChain<String> result = opChain.transform(operation);

        assertTrue(result instanceof ShortCircuitOperationChain<String>);

        verify(auditTrail, times(1)).addAudit("test-operation-name", true);
    }

    @Test
    public void testValidate_GivenValidation_ReturnsShortCircuitOperationChain() {
        ShortCircuitOperationChain<String> opChain = new ShortCircuitOperationChain<>(testException, auditTrail);

        OperationChain<String> result = opChain.validate(validation);

        assertTrue(result instanceof ShortCircuitOperationChain<String>);

        verify(auditTrail, times(1)).addAudit("test-validation-name", true);
    }

    @Test
    public void testGetState_ReturnsNull() {
        ShortCircuitOperationChain<String> opChain = new ShortCircuitOperationChain<>(testException, auditTrail);

        assertNull(opChain.getState());
    }

    @Test
    public void testGetAuditTrail_ReturnsAudits() {
        ShortCircuitOperationChain<String> opChain = new ShortCircuitOperationChain<>(testException, auditTrail);

        OperationChain<String> result1 = opChain.transform(operation);
        OperationChain<String> result2 = result1.validate(validation);

        AuditTrail auditTrail1 = result2.getAuditTrail();

        assertNotNull(auditTrail1);
        verify(auditTrail, times(1)).addAudit("test-operation-name", true);
        verify(auditTrail, times(1)).addAudit("test-validation-name", true);
    }

    @Test
    public void testGetFailureCause_ReturnsException() {
        ShortCircuitOperationChain<String> opChain = new ShortCircuitOperationChain<>(testException, auditTrail);

        assertEquals(testException, opChain.getFailureCause());
    }
}

package com.tfr.operation;

import com.tfr.operation.audit.AuditTrail;
import com.tfr.operation.exception.OperationException;
import com.tfr.operation.exception.ValidationException;
import com.tfr.operation.operation.Operation;
import com.tfr.operation.validation.Validation;

public class BasicOperationChain<I> implements OperationChain<I> {
    private final I state;
    private final AuditTrail auditTrail;

    private BasicOperationChain(I state) {
        this.state = state;
        this.auditTrail = new AuditTrail();
    }

    public BasicOperationChain(I state, AuditTrail auditTrail) {
        this.state = state;
        this.auditTrail = auditTrail;
    }

    public static <I> BasicOperationChain<I> of(I input) {
        return new BasicOperationChain<>(input);
    }

    @Override
    public <O> OperationChain<O> transform(Operation<I,O> operation) {
        try {
            O result = operation.execute(state);
            auditTrail.addAudit(operation.getName(), false);
            return new BasicOperationChain<>(result, auditTrail);
        } catch (OperationException ex) {
            auditTrail.addAudit(operation.getName(), false, ex);
            // return a short-circuit chain that audits "skipped"
            return new ShortCircuitOperationChain<>(ex, auditTrail);
        }
    }

    @Override
    public OperationChain<I> validate(Validation<I> validation) {
        try {
            validation.validate(state);
            auditTrail.addAudit(validation.getName(), false);
            return this;
        } catch (ValidationException ex) {
            auditTrail.addAudit(validation.getName(), false, ex);
            // return a short-circuit chain that audits "skipped"
            return new ShortCircuitOperationChain<>(ex, auditTrail);
        }
    }

    @Override
    public I getState() {
        return state;
    }

    @Override
    public AuditTrail getAuditTrail() {
        return auditTrail;
    }

}

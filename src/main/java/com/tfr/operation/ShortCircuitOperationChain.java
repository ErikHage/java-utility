package com.tfr.operation;

import com.tfr.operation.audit.AuditTrail;
import com.tfr.operation.exception.OperationChainException;
import com.tfr.operation.operation.Operation;
import com.tfr.operation.validation.Validation;

public class ShortCircuitOperationChain<I> implements OperationChain<I> {
    private final OperationChainException cause;
    private final AuditTrail auditTrail;

    ShortCircuitOperationChain(OperationChainException cause, AuditTrail auditTrail) {
        this.cause = cause;
        this.auditTrail = auditTrail;
    }

    @Override
    public <O> OperationChain<O> transform(Operation<I, O> operation) {
        auditTrail.addAudit(operation.getName(), true);
        return new ShortCircuitOperationChain<>(cause, auditTrail);
    }

    @Override
    public OperationChain<I> validate(Validation<I> validation) {
        auditTrail.addAudit(validation.getName(), true);
        return this;
    }

    @Override
    public I getState() {
        return null;
    }

    @Override
    public AuditTrail getAuditTrail() {
        return auditTrail;
    }

    public OperationChainException getFailureCause() {
        return cause;
    }
}

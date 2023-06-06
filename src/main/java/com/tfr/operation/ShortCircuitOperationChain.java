package com.tfr.operation;

import com.tfr.operation.audit.AuditTrail;
import com.tfr.operation.exception.OperationChainException;
import com.tfr.operation.operation.Operation;
import com.tfr.operation.validation.Validation;

import java.util.function.Consumer;
import java.util.function.Function;

public class ShortCircuitOperationChain<I> implements OperationChain<I> {
    private final OperationChainException cause;
    private final AuditTrail auditTrail;

    ShortCircuitOperationChain(OperationChainException cause, AuditTrail auditTrail) {
        this.cause = cause;
        this.auditTrail = auditTrail;
    }

    @Override
    public <O> OperationChain<O> transform(Operation<I, O> operation) {
        return transform(operation.getName(), operation::execute);
    }

    @Override
    public <O> OperationChain<O> transform(String operationName, Function<I, O> operation) {
        auditTrail.addAudit(operationName, true);
        return new ShortCircuitOperationChain<>(cause, auditTrail);
    }

    @Override
    public OperationChain<I> validate(Validation<I> validation) {
        return validate(validation.getName(), validation::validate);
    }

    @Override
    public OperationChain<I> validate(String validationName, Consumer<I> validation) {
        auditTrail.addAudit(validationName, true);
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

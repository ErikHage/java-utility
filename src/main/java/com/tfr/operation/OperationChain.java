package com.tfr.operation;

import com.tfr.operation.audit.AuditTrail;
import com.tfr.operation.operation.Operation;
import com.tfr.operation.validation.Validation;

public interface OperationChain<I> {
    <O> OperationChain<O> transform(Operation<I,O> operation);
    OperationChain<I> validate(Validation<I> validation);
    I getState();
    AuditTrail getAuditTrail();
}

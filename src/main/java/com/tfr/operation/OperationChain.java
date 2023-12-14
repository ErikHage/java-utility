package com.tfr.operation;

import com.tfr.operation.audit.AuditTrail;
import com.tfr.operation.operation.Operation;
import com.tfr.operation.validation.Validation;

import java.util.function.Consumer;
import java.util.function.Function;

public interface OperationChain<I> {

    <O> OperationChain<O> transform(Operation<I,O> operation);

    <O> OperationChain<O> transform(String operationName, Function<I,O> operation);

    OperationChain<I> validate(Validation<I> validation);

    OperationChain<I> validate(String validationName, Consumer<I> validation);

    I getState();

    AuditTrail getAuditTrail();
}

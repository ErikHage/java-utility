package com.tfr.operation;

import com.tfr.operation.audit.AuditTrail;
import com.tfr.operation.operation.Operation;
import com.tfr.operation.validation.Validation;

import java.util.function.Consumer;
import java.util.function.Function;

public interface OperationChain<I> {

    /**
     * Transforms the operation chain into a different generic type
     * @param operation Operation<Input, Output>
     * @return OperationChain<Output>
     * @param <O> Output type
     */
    <O> OperationChain<O> transform(Operation<I,O> operation);

    /**
     * Transforms the operation chain into a different generic type
     * @param operationName String - The name of the operation
     * @param operation Operation<Input, Output>
     * @return OperationChain<Output>
     * @param <O> Output type
     */
    <O> OperationChain<O> transform(String operationName, Function<I,O> operation);

    /**
     * Validates the current state of the OperationChain
     * @param validation Validation<Input>
     * @return OperationChain<Input>
     */
    OperationChain<I> validate(Validation<I> validation);

    /**
     * Validates the current state of the OperationChain
     * @param validationName String - The name of the validation
     * @param validation Validation<Input>
     * @return OperationChain<Input>
     */
    OperationChain<I> validate(String validationName, Consumer<I> validation);

    /**
     * Get the current state of the OperationChain
     * @return I
     */
    I getState();

    /**
     * Get the audit trail of the OperationChain, describing the outcome of
     * any completed or errored transformations and validations.
     * @return AuditTrail
     */
    AuditTrail getAuditTrail();
}

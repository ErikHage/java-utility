package com.tfr.operation;

import com.tfr.operation.audit.AuditTrail;
import com.tfr.operation.exception.OperationException;
import com.tfr.operation.exception.ValidationException;
import com.tfr.operation.operation.Operation;
import com.tfr.operation.validation.Validation;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * An implementation of an OperationChain that audits the transformation and validation steps
 * @param <I> type of state held
 */
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

    /**
     * Create an instance of BasicOperationChain with the given initial state
     * @param input initial state
     * @return BasicOperationChain
     * @param <I> state type
     */
    public static <I> BasicOperationChain<I> of(I input) {
        return new BasicOperationChain<>(input);
    }

    /**
     * Transform the state of the OperationChain from the input type I to the output type O
     * @param operation Operation<Input, Output>
     * @return OperationChain<O>
     */
    @Override
    public <O> OperationChain<O> transform(Operation<I,O> operation) {
       return transform(operation.getName(), operation::execute);
    }

    /**
     * Transform the state of the OperationChain from the input type I to the output type O
     * @param operationName String
     * @param operation Operation<Input, Output>
     * @return OperationChain<O>
     */
    @Override
    public <O> OperationChain<O> transform(String operationName, Function<I, O> operation) {
        try {
            O result = operation.apply(state);
            auditTrail.addAudit(operationName, false);
            return new BasicOperationChain<>(result, auditTrail);
        } catch (OperationException ex) {
            auditTrail.addAudit(operationName, false, ex);
            return new ShortCircuitOperationChain<>(ex, auditTrail);
        }
    }

    @Override
    public OperationChain<I> validate(Validation<I> validation) {
        return validate(validation.getName(), validation::validate);
    }

    @Override
    public OperationChain<I> validate(String validationName, Consumer<I> validation) {
        try {
            validation.accept(state);
            auditTrail.addAudit(validationName, false);
            return this;
        } catch (ValidationException ex) {
            auditTrail.addAudit(validationName, false, ex);
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

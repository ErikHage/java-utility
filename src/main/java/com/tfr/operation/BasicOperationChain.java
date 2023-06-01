package com.tfr.operation;

import com.tfr.operation.exception.OperationException;
import com.tfr.operation.exception.ValidationException;

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
            // do audit
            return new BasicOperationChain<>(result, auditTrail);
        } catch (OperationException ex) {
            // return a short-circuit chain that audits "skipped"
            return new ShortCircuitOperationChain<>(ex);
        }
    }

    @Override
    public OperationChain<I> validate(Validation<I> validation) {
        try {
            validation.validate(state);
            // do audit
            return this;
        } catch (ValidationException ex) {
            // do audit
            // return a short-circuit chain that audits "skipped"
            return new ShortCircuitOperationChain<>(ex);
        }
    }

    @Override
    public I getState() {
        return state;
    }

}

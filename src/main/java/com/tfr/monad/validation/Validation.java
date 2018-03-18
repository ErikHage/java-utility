package com.tfr.monad.validation;

import com.tfr.monad.model.FailedValidation;
import com.tfr.monad.model.SuccessfulValidation;
import com.tfr.monad.model.ValidationResult;

@FunctionalInterface
public interface Validation<IN> {

    default String getName() {
        return this.getClass().getName();
    }

    default SuccessfulValidation succeed() {
        return new SuccessfulValidation(getName());
    }

    default FailedValidation fail(String reason) {
        return new FailedValidation(getName(), reason);
    }

    default ValidationResult validate(IN input) {
        try {
            verify(input);
        } catch (ValidationException ex) {
            return fail(ex.getMessage());
        }
        return succeed();
    }

    void verify(IN input) throws ValidationException;

}

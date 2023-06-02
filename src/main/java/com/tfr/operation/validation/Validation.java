package com.tfr.operation.validation;

import com.tfr.operation.exception.ValidationException;

public interface Validation<I> {
    String getName();
    void validate(I input) throws ValidationException;
}

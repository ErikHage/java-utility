package com.tfr.operation.operation;

import com.tfr.operation.exception.OperationException;

public interface Operation<I,O> {
    String getName();
    O execute(I input) throws OperationException;
}

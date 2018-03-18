package com.tfr.monad.transformation;

import com.tfr.monad.model.FailedTransformation;
import com.tfr.monad.model.SuccessfulTransformation;

@FunctionalInterface
public interface Transformation<IN, OUT> {

    default String getName() {
        return this.getClass().getName();
    }

//    default SuccessfulTransformation succeed() {
//        return new SuccessfulTransformation(getName());
//    }
//
//    default FailedTransformation fail(String reason) {
//        return new FailedTransformation(getName(), reason);
//    }

    OUT execute(IN input) throws TransformationException;
}

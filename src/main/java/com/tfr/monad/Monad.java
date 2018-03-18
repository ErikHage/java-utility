package com.tfr.monad;

import com.tfr.monad.transformation.Transformation;
import com.tfr.monad.validation.Validation;
import com.tfr.monad.validation.ValidationException;

public interface Monad<IN> {

    <OUT> Monad<OUT> map(Transformation<IN, OUT> transformation);

    <OUT> Monad<OUT> flatMap(Transformation<IN, Monad<OUT>> transformation);

    Monad<IN> validate(Validation<IN> validation) throws ValidationException;

    IN getValue();

}

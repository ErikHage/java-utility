package com.tfr.monad;

import com.tfr.monad.transformation.Transformation;
import com.tfr.monad.validation.Validation;
import com.tfr.monad.validation.ValidationException;

public class State<IN> {

    protected IN value;

    private State(IN value) {
        this.value = value;
    }

    public static <IN> State<IN> of(IN value) {
        return new State<>(value);
    }

    public <OUT> State<OUT> map(Transformation<IN, OUT> transformation) {
        return new State<>(transformation.execute(value));
    }

    public <OUT> State<OUT> flatMap(Transformation<IN, State<OUT>> transformation) {
        State<OUT> result = transformation.execute(value);
        return new State<>(result.value);
    }

    public State<IN> validate(Validation<IN> validation) throws ValidationException {
        validation.verify(value);
        return this;
    }

    public IN getValue() {
        return this.value;
    }
}

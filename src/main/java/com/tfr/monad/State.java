package com.tfr.monad;

import com.tfr.monad.transformation.Transformation;
import com.tfr.monad.validation.Validation;
import com.tfr.monad.validation.ValidationException;

public class State<IN> implements Monad<IN> {

    protected IN value;

    protected State(IN value) {
        this.value = value;
    }

    public static <IN> State<IN> of(IN value) {
        return new State<>(value);
    }

    @Override
    public <OUT> State<OUT> map(Transformation<IN, OUT> transformation) {
        return new State<>(transformation.execute(value));
    }

    @Override
    public <OUT> State<OUT> flatMap(Transformation<IN, Monad<OUT>> transformation) {
        State<OUT> result = (State<OUT>) transformation.execute(value);
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

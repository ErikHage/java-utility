package com.tfr.monad;

import com.tfr.monad.model.Result;
import com.tfr.monad.transformation.Transformation;
import com.tfr.monad.validation.Validation;
import com.tfr.monad.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class Writer<IN> {

    private IN value;
    private List<Result> log;

    private Writer(IN value, List<Result> log) {
        this.value = value;
        this.log = new ArrayList<>(log);
    }

    public static <IN> Writer<IN> of(IN value) {
        return new Writer<>(value, new ArrayList<>());
    }

    public <OUT> Writer<OUT> map(Transformation<IN, OUT> transformation) {
        OUT result = transformation.execute(value);
        return new Writer<>(result, log);
    }

    public <OUT> Writer<OUT> flatMap(Transformation<IN, Writer<OUT>> transformation) {
        Writer<OUT> result = transformation.execute(value);

        return new Writer<>(result.getValue(), log);
    }

    public Writer<IN> validate(Validation<IN> validation) throws ValidationException {
        log.add(validation.validate(value));
        return this;
    }

    public IN getValue() {
        return value;
    }

    public List<Result> getLog() {
        return log;
    }
}

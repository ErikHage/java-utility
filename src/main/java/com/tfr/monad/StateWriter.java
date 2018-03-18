package com.tfr.monad;

import com.tfr.monad.model.Result;
import com.tfr.monad.transformation.Transformation;
import com.tfr.monad.transformation.TransformationException;
import com.tfr.monad.validation.Validation;
import com.tfr.monad.validation.ValidationException;

import java.util.ArrayList;
import java.util.List;

public class StateWriter<IN> extends State<IN> {

    private List<Result> log;

    protected StateWriter(IN value, List<Result> log) {
        super(value);
        this.log = log;
    }

    public static <IN> StateWriter<IN> of(IN value) {
        return new StateWriter<>(value, new ArrayList<>());
    }

    @Override
    public <OUT> State<OUT> map(Transformation<IN, OUT> transformation) {
        OUT result;
        try {
            result = transformation.execute(value);
        } catch (TransformationException ex) {
//            log.add(transformation.fail(ex.getMessage()));
//            return null; //TODO figure out what to do here, maybe a log-holding exception
            throw ex;
        }
        return new StateWriter<>(result, log);
    }

    @Override
    public <OUT> State<OUT> flatMap(Transformation<IN, Monad<OUT>> transformation) {
        return super.flatMap(transformation); //TODO this is wrong
    }

    @Override
    public State<IN> validate(Validation<IN> validation) throws ValidationException {
        log.add(validation.validate(value));
        return this;
    }

    public List<Result> getLog() {
        return log;
    }
}

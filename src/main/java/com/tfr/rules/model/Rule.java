package com.tfr.rules.model;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 *
 * Created by Erik on 12/6/2016.
 */
public abstract class Rule<T,R> {

    private String name;
    private int priority;

    private Predicate<T> predicate;

    private Function<T,R> resultFunction;

    public boolean evaluate(T t) {
        return predicate.test(t);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Predicate<T> getPredicate() {
        return predicate;
    }

    public void setPredicate(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Function<T, R> getResultFunction() {
        return resultFunction;
    }

    public void setResultFunction(Function<T, R> resultFunction) {
        this.resultFunction = resultFunction;
    }
}

package com.tfr.lazy;

import java.util.function.Supplier;

/**
 * Lazily loads a value of type T using a Supplier provided upon instantiation. Useful
 * for situations where obtaining the value is time or memory intensive, or the field
 * may not need to be accessed.
 *
 * @param <T> : Object type
 *
 * Created by Erik on 12/5/2016.
 */
public class Lazy<T> {

    private T value;
    private Supplier<T> supplier;

    public Lazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T getValue() {
        if(value == null) {
            value = supplier.get();
        }
        return value;
    }

}

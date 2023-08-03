package com.tfr.either;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class Left<L, R> extends Either<L, R> {
    private final L value;

    Left(L value) {
        this.value = requireNonNull(value);
    }

    @Override
    public <R2> Either<L, R2> map(Function<? super R, ? extends R2> mapper) {
        @SuppressWarnings("unchecked")
        Either<L, R2> result = (Either<L, R2>) this;
        return result;
    }

    @Override
    public <R2> Either<L, R2> flatMap(Function<? super R, ? extends Either<? extends L, ? extends R2>> mapper) {
        @SuppressWarnings("unchecked")
        Either<L, R2> result = (Either<L, R2>) this;
        return result;
    }

    @Override
    public Either<L, R> filter(Function<? super R, Optional<? extends L>> predicate) {
        return this;
    }

    @Override
    public <L2> Either<L2, R> mapLeft(Function<? super L, ? extends L2> mapper) {
        return new Left<>(mapper.apply(value));
    }

    @Override
    public <L2> Either<L2, R> flatMapLeft(Function<? super L, ? extends Either<? extends L2, ? extends R>> mapper) {
        @SuppressWarnings("unchecked")
        Either<L2, R> result = (Either<L2, R>) mapper.apply(value);
        return result;
    }

    @Override
    public Either<L, R> filterLeft(Function<? super L, Optional<? extends R>> predicate) {
        Optional<? extends R> test = predicate.apply(value);
        if (test.isEmpty()) {
            return this;
        }
        return new Right<>(test.orElseThrow());
    }

    @Override
    public <U> U fold(Function<? super L, ? extends U> leftMapper, Function<? super R, ? extends U> rightMapper) {
        return leftMapper.apply(value);
    }

    @Override
    public void ifLeftOrElse(Consumer<? super L> leftAction, Consumer<? super R> rightAction) {
        leftAction.accept(value);
    }

    @Override
    public <X extends Throwable> R orElseThrow(Function<? super L, ? extends X> exceptionSupplier) throws X {
        throw exceptionSupplier.apply(value);
    }

    @Override
    public boolean isLeft() {
        return true;
    }

    @Override
    public Optional<L> getLeft() {
        return Optional.of(value);
    }

    @Override
    public Optional<R> getRight() {
        return Optional.empty();
    }
}

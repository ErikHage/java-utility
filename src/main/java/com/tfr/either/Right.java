package com.tfr.either;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Internal implementation of a Right-Either.
 *
 * @param <L> the type of the LHS value
 * @param <R> the type of the RHS value
 */
public class Right<L,R> extends Either<L,R> {
    private final R value;

    Right(R value) {
        this.value = value;
    }

    @Override
    public <R2> Either<L, R2> map(Function<? super R, ? extends R2> mapper) {
        return new Right<>(mapper.apply(value));
    }

    @Override
    public <R2> Either<L, R2> flatMap(Function<? super R, ? extends Either<? extends L, ? extends R2>> mapper) {
        @SuppressWarnings("unchecked")
        Either<L, R2> result = (Either<L, R2>) mapper.apply(value);
        return result;
    }

    @Override
    public Either<L, R> filter(Function<? super R, Optional<? extends L>> predicate) {
        Optional<? extends L> test = predicate.apply(value);
        if (test.isEmpty()) {
            return this;
        }
        return new Left<>(test.orElseThrow());
    }

    @Override
    public <L2> Either<L2, R> mapLeft(Function<? super L, ? extends L2> mapper) {
        @SuppressWarnings("unchecked")
        Either<L2, R> result = (Either<L2, R>) this;
        return result;
    }

    @Override
    public <L2> Either<L2, R> flatMapLeft(Function<? super L, ? extends Either<? extends L2, ? extends R>> mapper) {
        @SuppressWarnings("unchecked")
        Either<L2, R> result = (Either<L2, R>) this;
        return result;
    }

    @Override
    public Either<L, R> filterLeft(Function<? super L, Optional<? extends R>> predicate) {
        return this;
    }

    @Override
    public <U> U fold(
            Function<? super L, ? extends U> leftMapper,
            Function<? super R, ? extends U> rightMapper) {
        return rightMapper.apply(value);
    }

    @Override
    public void ifLeftOrElse(Consumer<? super L> leftAction, Consumer<? super R> rightAction) {
        rightAction.accept(value);
    }

    @Override
    public <X extends Throwable> R orElseThrow(Function<? super L, ? extends X> exceptionSupplier) throws X {
        return value;
    }

    @Override
    public boolean isLeft() {
        return false;
    }

    @Override
    public Optional<L> getLeft() {
        return Optional.empty();
    }

    @Override
    public Optional<R> getRight() {
        return Optional.of(value);
    }
}

package com.tfr.either;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Either<L, R> {

    Either() {}

    public static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }

    public abstract <R2> Either<L, R2> map(
            Function<? super R, ? extends R2> mapper);

    public abstract <R2> Either<L, R2> flatMap(
            Function<? super R, ? extends Either<? extends L, ? extends R2>> mapper);

    public abstract Either<L, R> filter(
            Function<? super R, Optional<? extends L>> predicate);

    public abstract <L2> Either<L2, R> mapLeft(
            Function<? super L, ? extends L2> mapper);

    public abstract <L2> Either<L2, R> flatMapLeft(
            Function<? super L, ? extends Either<? extends L2, ? extends R>> mapper);

    public abstract Either<L, R> filterLeft(
            Function<? super L, Optional<? extends R>> predicate);

    public abstract <U> U fold(
            Function<? super L, ? extends U> leftMapper,
            Function<? super R, ? extends U> rightMapper);

    public abstract void ifLeftOrElse(
            Consumer<? super L> leftAction, 
            Consumer<? super R> rightAction);

    public abstract <X extends Throwable> R orElseThrow(
            Function<? super L, ? extends X> exceptionSupplier) throws X;

    public abstract boolean isLeft();

    public final boolean isRight() {
        return !isLeft();
    }

    public abstract Optional<L> getLeft();

    public abstract Optional<R> getRight();


}

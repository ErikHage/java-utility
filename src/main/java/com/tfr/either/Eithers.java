package com.tfr.either;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public final class Eithers {

    private static final Set<Collector.Characteristics> CH_NOID = Set.of();

    public static <L, R> Collector<Either<? extends L, ? extends R>, ?, Either<L, List<R>>> firstFailure() {
        BiConsumer<FirstFailureAcc<L, R>, Either<? extends L, ? extends R>> accumulator =
                (acc, either) -> either.ifLeftOrElse(acc::addLeft, acc::addRight);

        BinaryOperator<FirstFailureAcc<L, R>> combiner = (acc, other) ->
                (FirstFailureAcc<L, R>) acc.combine(other);

        return new CollectorImpl<>(FirstFailureAcc::new, accumulator, combiner, FirstFailureAcc::finish);
    }

    public static <L, R> Collector<Either<? extends L, ? extends R>, ?, Either<List<L>, List<R>>> allFailures() {
        BiConsumer<AllFailuresAcc<L, R>, Either<? extends L, ? extends R>> accumulator = (acc, either) ->
                either.ifLeftOrElse(acc::addLeft, acc::addRight);

        BinaryOperator<AllFailuresAcc<L, R>> combiner = (acc, other) ->
                (AllFailuresAcc<L, R>) acc.combine(other);

        return new CollectorImpl<>(AllFailuresAcc::new, accumulator, combiner, AllFailuresAcc::finish);
    }

    public static <T> Collector<T, ?, Optional<List<T>>> toOptionalList() {
        return Collectors.collectingAndThen(Collectors.toList(), Eithers::optionalList);
    }

    public static <T> Optional<List<T>> optionalList(List<? extends T> values) {
        if (values.isEmpty()) {
            return Optional.empty();
        }
        @SuppressWarnings("unchecked")
        List<T> result = (List<T>) values;
        return Optional.of(result);
    }

    private static final class CollectorImpl<T, A, R> implements Collector<T, A, R> {
        final Supplier<A> supplier;
        final BiConsumer<A, T> accumulator;
        final BinaryOperator<A> combiner;
        final Function<A, R> finisher;

        CollectorImpl(
                Supplier<A> supplier,
                BiConsumer<A, T> accumulator,
                BinaryOperator<A> combiner,
                Function<A, R> finisher) {
            this.supplier = supplier;
            this.accumulator = accumulator;
            this.combiner = combiner;
            this.finisher = finisher;
        }

        @Override
        public Supplier<A> supplier() {
            return supplier;
        }

        @Override
        public BiConsumer<A, T> accumulator() {
            return accumulator;
        }

        @Override
        public BinaryOperator<A> combiner() {
            return combiner;
        }

        @Override
        public Function<A, R> finisher() {
            return finisher;
        }

        @Override
        public Set<Characteristics> characteristics() {
            return CH_NOID;
        }
    }

    static abstract class Acc<L, C, R> {
        private ArrayList<R> right;

        abstract void combineLeft(C otherLeft);

        abstract C leftColl();

        abstract void addLeft(L left);

        final void addRight(R value) {
            if (leftColl() != null) {
                return;
            }
            if (right == null) {
                right = new ArrayList<>();
            }
            right.add(value);
        }

        final Acc<L, C, R> combine(Acc<L, C, R> other) {
            if (leftColl() != null) {
                combineLeft(other.leftColl());
                return this;
            }
            if (other.leftColl() != null) {
                return other;
            }
            if (other.right == null) {
                return this;
            }
            if (right == null) {
                right = other.right;
            } else {
                right.addAll(other.right);
            }
            return this;
        }

        final Either<C, List<R>> finish() {
            C left = leftColl();
            return left != null
                    ? Either.left(left)
                    : Either.right(right == null ? List.of() : right);
        }
    }

    private static final class FirstFailureAcc<L, R> extends Acc<L, L, R> {
        L left;

        @Override
        void combineLeft(L otherLeft) {
            addLeft(otherLeft);
        }

        @Override
        void addLeft(L value) {
            if (left == null) {
                left = value;
            }
        }

        @Override
        L leftColl() {
            return left;
        }
    }

    private static final class AllFailuresAcc<L, R> extends Acc<L, List<L>, R> {
        List<L> left;

        @Override
        void combineLeft(List<L> otherLeft) {
            if (left == null) {
                left = otherLeft;
            } else if (otherLeft != null) {
                left.addAll(otherLeft);
            }
        }

        @Override
        List<L> leftColl() {
            return left;
        }

        @Override
        void addLeft(L value) {
            if (left == null) {
                left = new ArrayList<>();
            }
            left.add(value);
        }
    }

    private Eithers() {

    }
}

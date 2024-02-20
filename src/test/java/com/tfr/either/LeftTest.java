package com.tfr.either;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class LeftTest {

    @Test
    void shouldNotMapLeftValue() {
        Either<String, Integer> either = Either.left("string");

        Either<String, Double> result = either.map(r -> r * 1.1);

        assertEquals("string", result.getLeft().orElse("not string"));
        assertEquals(Optional.empty(), result.getRight());
    }

    @Test
    void shouldGetLeft() {
        Either<String, Integer> either = Either.left("string");

        assertEquals("string", either.getLeft().orElse("not"));
    }

    @Test
    void shouldNotFlatMapLeft() {
        Either<String, Integer> either = Either.left("string");

        Either<String, Double> result = either.flatMap(r -> Either.right(r * 1.2));

        assertEquals("string", result.getLeft().orElse("not string"));
        assertEquals(Optional.empty(), result.getRight());
    }

    @Test
    void shouldNotFilter() {
        Either<String, Integer> either = Either.left("string");

        Either<String, Integer> result = either.filter(r -> Optional.of("xxx"));

        assertEquals("string", result.getLeft().orElse("not string"));
    }

    @Test
    void shouldMapLeft() {
        Either<String, Integer> either = Either.left("string");

        Either<String, Integer> result = either.mapLeft(l -> "other string");

        assertEquals("other string", result.getLeft().orElse("not string"));
    }

    @Test
    void shouldFlatMapLeft() {
        Either<String, Integer> either = Either.left("string");

        Either<String, Integer> result = either.flatMapLeft(l -> Either.left("new string"));

        assertEquals("new string", result.getLeft().orElse("not string"));
        assertEquals(Optional.empty(), result.getRight());
    }

    @Test
    void shouldNotFilterLeft() {
        Either<String, Integer> either = Either.left("string");

        Either<String, Integer> result = either.filterLeft(l -> Optional.empty());

        assertEquals("string", result.getLeft().orElse("not string"));
        assertEquals(Optional.empty(), result.getRight());
    }

    @Test
    void shouldFilterLeft() {
        Either<String, Integer> either = Either.left("string");

        Either<String, Integer> result = either.filterLeft(l -> Optional.of(1));

        assertEquals(Optional.of(1), result.getRight());
    }

    @Test
    void shouldFoldLeft() {
        Either<String, Integer> either = Either.left("string");

        String result = either.fold(
                l -> "new string",
                r -> "not string"
        );

        assertEquals("new string", result);
    }

    @Test
    void shouldActionIfLeft() {
        Either<String, Integer> either = Either.left("string");

        either.ifLeftOrElse(
                l -> {},
                r -> {
                    fail("called right action, should have called left action");
                }
        );
    }
}
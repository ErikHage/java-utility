package com.tfr.either;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EitherTest {

    @Test
    void shouldReturnALeftInstance() {
        Either<String, Integer> either = Either.left("a");

        assertTrue(either instanceof Left);
    }

    @Test
    void shouldReturnARightInstance() {
        Either<String, Integer> either = Either.right(1);

        assertTrue(either instanceof Right);
    }

    @Test
    void shouldMapLeft() {
        Either<String, Integer> either = Either.left("a");

        Either<String, Integer> result = either.mapLeft(left -> left + "b");

        assertEquals("ab", result.getLeft().orElse("c"));
    }

    @Test
    void shouldMapRight() {
        Either<String, Integer> either = Either.right(10);

        Either<String, Integer> result = either.map(r -> r + 2);

        assertEquals(12, result.getRight().orElse(100));
    }

    @Test
    void shouldFlatMapLeft() {
        Either<String, Integer> either = Either.left("abcd");

        Either<Integer, Integer> result = either.flatMapLeft(left -> Either.left(left.length()));

        assertEquals(4, result.getLeft().orElse(100));
    }

    @Test
    void shouldFlatMapRight() {
        Either<String, Integer> either = Either.right(8);

        Either<String, Double> result = either.flatMap(right -> Either.right(right * 1.5));

        assertEquals(12, result.getRight().orElse(77.5));
    }

    @Test
    void shouldFilterLeft() {
        Either<String, Integer> either = Either.left("abcd");

        Either<String, Integer> result = either.filterLeft((left) -> {
            if ("abcd".equals(left)) {
              return Optional.of(10);
            }
            return Optional.empty();
        });

        assertEquals(10, result.getRight().orElse(1));
    }
}
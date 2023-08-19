package com.tfr.either;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RightTest {

    @Test
    void shouldMap() {
        Either<String, Integer> right = Either.right(10);

        Either<String, Double> result = right.map(i -> i * 1.5);

        assertEquals(15.0, result.getRight().orElse(0.0));
    }

    @Test
    void shouldFlatMap() {
        Either<String, Integer> right = Either.right(10);

        Either<String, Double> result = right.flatMap(i -> Either.right(i * 1.5));

        assertEquals(15.0, result.getRight().orElse(0.0));
    }

    @Test
    void shouldFilter() {
        Either<String, Integer> right = Either.right(10);

        Either<String, Integer> result = right.filter(r -> Optional.empty());

        assertEquals(10, result.getRight().orElse(999));
    }

    @Test
    void shouldBeRight() {
        Either<String, Integer> right = Either.right(10);

        assertTrue(right.isRight());
    }
}
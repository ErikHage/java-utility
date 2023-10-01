package com.tfr.either;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
}
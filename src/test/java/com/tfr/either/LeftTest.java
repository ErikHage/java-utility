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
    void shouldNotMapRightValue() {
        Either<String, Integer> either = Either.left("string");

        Either<String, Double> result = either.map(r -> r * 1.1);

        assertEquals("string", result.getLeft().orElse("not string"));
        assertEquals(Optional.empty(), result.getRight());
    }
}
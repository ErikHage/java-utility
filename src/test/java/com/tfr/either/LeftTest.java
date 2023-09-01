package com.tfr.either;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeftTest {

    @Test
    void shouldMapRight() {
        Either<String, Integer> either = Either.left("string");

        Either<String, Double> result = either.map(r -> r * 1.1);

        assertEquals("string", result.getLeft().orElse("not string"));
    }
}
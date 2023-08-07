package com.tfr.either;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EitherTest {

    @Test
    void shouldReturnALeftInstance() {
        Either<String, Integer> result = Either.left("a");

        assertTrue(result instanceof Left);
    }

    @Test
    void shouldReturnARightInstance() {
        Either<String, Integer> result = Either.right(1);

        assertTrue(result instanceof Right);
    }

    @Test
    void shouldMapLeft() {
        Either<String, Integer> result = Either.left("a");

        Either<String, Integer> mapResult = result.mapLeft(left -> left + "b");

        assertEquals("ab", mapResult.getLeft().orElse("c"));
    }
}
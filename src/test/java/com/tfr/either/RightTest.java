package com.tfr.either;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RightTest {

    @Test
    void shouldBeRight() {
        Either<String, Integer> right = Either.right(10);

        assertTrue(right.isRight());
    }
}
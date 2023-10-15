package com.tfr.either;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EithersTest {

    @Test
    void shouldGetFirstFailureWhenRight() {
        Either<String, Integer> either = Either.right(1);

        Either<String, List<Integer>> result = Optional.of(either).stream().collect(Eithers.firstFailure());

        assertEquals(List.of(1), result.getRight().orElse(List.of()));
    }
}
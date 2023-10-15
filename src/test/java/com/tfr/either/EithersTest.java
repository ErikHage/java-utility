package com.tfr.either;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EithersTest {

    @Test
    void shouldGetListOfRightsWhenFirstFailureCollectHasOnlyRights() {
        List<Either<String, Integer>> eithers = List.of(
                Either.right(1),
                Either.right(2)
        );

        Either<String, List<Integer>> result = eithers.stream().collect(Eithers.firstFailure());

        assertEquals(List.of(1, 2), result.getRight().orElse(List.of()));
    }

    @Test
    void shouldGetFirstLeftWhenFirstFailureCollectHasMultipleLefts() {
        List<Either<String, Integer>> eithers = List.of(
                Either.right(1),
                Either.left("first"),
                Either.right(2),
                Either.left("last")
        );

        Either<String, List<Integer>> result = eithers.stream().collect(Eithers.firstFailure());

        assertEquals("first", result.getLeft().orElse("wrong"));
    }
}
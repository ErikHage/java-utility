package com.tfr.either;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

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
    void shouldGetFirstLeftWhenFirstFailureCollect() {
        List<Either<String, Integer>> eithers = List.of(
                Either.right(1),
                Either.left("first"),
                Either.right(2)
        );

        Either<String, List<Integer>> result = eithers.stream().collect(Eithers.firstFailure());

        assertEquals("first", result.getLeft().orElse("wrong"));
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

    @Test
    void shouldGetListOfRightsWhenAllFailuresCollectHasOnlyRights() {
        List<Either<String, Integer>> eithers = List.of(
                Either.right(1),
                Either.right(2)
        );

        Either<List<String>, List<Integer>> result = eithers.stream().collect(Eithers.allFailures());

        assertEquals(List.of(1, 2), result.getRight().orElse(List.of()));
    }

    @Test
    void shouldGetListOfLeftWhenAllFailuresCollectHasOnlyLefts() {
        List<Either<String, Integer>> eithers = List.of(
                Either.left("first"),
                Either.left("second")
        );

        Either<List<String>, List<Integer>> result = eithers.stream().collect(Eithers.allFailures());

        assertEquals(List.of("first", "second"), result.getLeft().orElse(List.of()));
    }

    @Test
    void shouldCollectToOptionalListOfLefts() {
        List<Either<String, Integer>> eithers = List.of(
                Either.left("first"),
                Either.left("second")
        );

        Optional<List<Either<String, Integer>>> result = eithers.stream().collect(Eithers.toOptionalList());

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertEquals("first", result.get().get(0).getLeft().orElse("xxx"));
        assertEquals("second", result.get().get(1).getLeft().orElse("xxx"));
    }

    @Test
    void shouldCollectToOptionalListOfRights() {
        List<Either<String, Integer>> eithers = List.of(
                Either.right(1),
                Either.right(2)
        );

        Optional<List<Either<String, Integer>>> result = eithers.stream().collect(Eithers.toOptionalList());

        assertTrue(result.isPresent());
        assertEquals(2, result.get().size());
        assertEquals(1, result.get().get(0).getRight().orElse(9));
        assertEquals(2, result.get().get(1).getRight().orElse(9));
    }

    @Test
    void shouldCollectToOptionalListOfRightsAndLefts() {
        List<Either<String, Integer>> eithers = List.of(
                Either.right(1),
                Either.left("first"),
                Either.left("second"),
                Either.right(2)
        );

        Optional<List<Either<String, Integer>>> result = eithers.stream().collect(Eithers.toOptionalList());

        assertTrue(result.isPresent());
        assertEquals(4, result.get().size());
        assertEquals(1, result.get().get(0).getRight().orElse(9));
        assertEquals("first", result.get().get(1).getLeft().orElse("x"));
        assertEquals("second", result.get().get(2).getLeft().orElse("x"));
        assertEquals(2, result.get().get(3).getRight().orElse(9));

    }
}
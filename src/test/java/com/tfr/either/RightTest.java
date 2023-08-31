package com.tfr.either;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.Consumer;

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
    void shouldFilterAndReturnThis() {
        Either<String, Integer> right = Either.right(10);

        Either<String, Integer> result = right.filter(r -> Optional.empty());

        assertEquals(10, result.getRight().orElse(999));
    }

    @Test
    void shouldFilterAndReturnLeft() {
        Either<String, Integer> right = Either.right(10);

        Either<String, Integer> result = right.filter(r -> Optional.of("xxx"));

        assertTrue(result.isLeft());
    }

    @Test
    void shouldMapLeft() {
        Either<String, Integer> right = Either.right(10);

        Either<String, Integer> result = right.mapLeft(l -> l + 5);

        assertSame(right, result);
    }

    @Test
    void shouldFlatMapLeft() {
        Either<String, Integer> right = Either.right(10);

        Either<String, Integer> result = right.flatMapLeft(l -> Either.left("test"));

        assertSame(right, result);
    }

    @Test
    void shouldFilterLeft() {
        Either<String, Integer> right = Either.right(10);

        Either<String, Integer> result = right.filterLeft(l -> Optional.empty());

        assertSame(right, result);
    }

    @Test
    void shouldFoldRight() {
        Either<String, Integer> right = Either.right(10);

        String result = right.fold(l -> "s", r -> "x");

        assertEquals("x", result);
    }

    @Test
    void shouldFoldLeft() {
        Either<String, Integer> right = Either.left("string");

        String result = right.fold(l -> "s", r -> "x");

        assertEquals("s", result);
    }

    @Test
    void shouldBeRight() {
        Either<String, Integer> right = Either.right(10);

        assertTrue(right.isRight());
    }

    @Test
    void shouldReturnEmptyOptionalCallingGetLeft() {
        Either<String, Integer> right = Either.right(10);

        assertEquals(Optional.empty(), right.getLeft());
    }

    @Test
    void shouldThrowWhenLeftOnOrElseThrow() {
        Either<String, Integer> right = Either.left("some");

        assertThrows(Exception.class, () -> {
            right.orElseThrow((l) -> new Exception("message"));
        });
    }

    @Test
    void shouldNotThrowWhenRightOnOrElseThrow() throws Exception {
        Either<String, Integer> right = Either.right(10);

        int result = right.orElseThrow((l) -> new Exception("message"));

        assertEquals(10, result);
    }

    @Test
    void shouldOrElseIfLeftOrElse() {
        Either<String, Integer> right = Either.right(10);

        Consumer<String> leftConsumer = (s) -> fail("should have been a Right instance");
        Consumer<Integer> rightConsumer = (i) -> assertEquals(10, i);

        right.ifLeftOrElse(leftConsumer, rightConsumer);
    }
}
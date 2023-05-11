package com.tfr.monad.transformation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransformationTest {

    private Transformation<String, Integer> transformation;

    @BeforeEach
    public void setUp() {
        transformation = new TestTransformation();
    }

    @Test
    public void testGetName_ExpectClassName() {
        assertEquals(TestTransformation.class.getName(), transformation.getName());
    }

    @Test
    public void testExecute_GivenValidInput_ExpectSuccess() {
        Integer result = transformation.execute("1");

        assertEquals(1, result, 0);
    }

    @Test
    public void testExecute_GivenInvalidInput_ExpectException() {
        assertThrows(TransformationException.class, () -> {
            transformation.execute("abc");
        });
    }

    @Test
    public void testTransformationException() {
        assertThrows(TransformationException.class, () -> {
            throw new TransformationException("message");
        });
    }

    private static class TestTransformation implements Transformation<String, Integer> {
        @Override
        public Integer execute(String input) throws TransformationException {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                throw new TransformationException("failed to transform", ex);
            }
        }
    }
}

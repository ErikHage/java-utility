package com.tfr.monad.transformation;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransformationTest {

    private Transformation<String, Integer> transformation;

    @Before
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

    @Test (expected = Exception.class)
    public void testExecute_GivenInvalidInput_ExpectException() {
        transformation.execute("abc");
    }

    private static class TestTransformation implements Transformation<String, Integer> {
        @Override
        public Integer execute(String input) throws TransformationException {
            return Integer.parseInt(input);
        }
    }
}

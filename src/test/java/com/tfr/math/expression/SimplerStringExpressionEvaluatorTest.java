package com.tfr.math.expression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimplerStringExpressionEvaluatorTest {

    @Test
    public void testEvaluate_GivenAddition_ExpectSum() {
        int result = SimplerStringExpressionEvaluator.evaluate("10 + 2");

        assertEquals(12, result);
    }

    @Test
    public void testEvaluate_GivenSubtraction_ExpectDifference() {
        int result = SimplerStringExpressionEvaluator.evaluate("10 - 2");

        assertEquals(8, result);
    }

    @Test
    public void testEvaluate_GivenMultiplication_ExpectProduct() {
        int result = SimplerStringExpressionEvaluator.evaluate("10 * 2");

        assertEquals(20, result);
    }

    @Test
    public void testEvaluate_GivenDivision_ExpectFraction() {
        int result = SimplerStringExpressionEvaluator.evaluate("10 / 2");

        assertEquals(5, result);
    }

    @Test
    public void testEvaluate_GivenDivisionBy0_ExpectUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            SimplerStringExpressionEvaluator.evaluate("10 / 0");
        });
    }

    @Test
    public void testEvaluate_GivenAdditionAndMultiplicationOperators_ExpectOrderOfOperationsRespected() {
        int result = SimplerStringExpressionEvaluator.evaluate("10 + 2 * 6");

        assertEquals(22, result);
    }

    @Test
    public void testEvaluate_GivenAdditionAndMultiplicationOperators2_ExpectOrderOfOperationsRespected() {
        int result = SimplerStringExpressionEvaluator.evaluate("100 * 2 + 12");

        assertEquals(212, result);
    }

    @Test
    public void testEvaluate_GivenSubtractionAndMultiplicationOperators_ExpectOrderOfOperationsRespected() {
        int result = SimplerStringExpressionEvaluator.evaluate("10 - 2 * 6");

        assertEquals(-2, result);
    }

    @Test
    public void testEvaluate_GivenAllOperators_ExpectOrderOfOperationsRespected() {
        int result = SimplerStringExpressionEvaluator.evaluate("5 * 10 - 2 * 6 + 100");

        assertEquals(138, result);
    }
}

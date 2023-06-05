package com.tfr.math.expression;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SimplerStringExpressionEvaluatorTest {

    private SimplerStringExpressionEvaluator evaluator;

    @BeforeEach
    public void setUp() {
        evaluator = new SimplerStringExpressionEvaluator();
    }

    @Test
    public void testEvaluate_GivenAddition_ExpectSum() {
        int result = evaluator.evaluate("10 + 2");

        assertEquals(12, result);
    }

    @Test
    public void testEvaluate_GivenSubtraction_ExpectDifference() {
        int result = evaluator.evaluate("10 - 2");

        assertEquals(8, result);
    }

    @Test
    public void testEvaluate_GivenMultiplication_ExpectProduct() {
        int result = evaluator.evaluate("10 * 2");

        assertEquals(20, result);
    }

    @Test
    public void testEvaluate_GivenDivision_ExpectFraction() {
        int result = evaluator.evaluate("10 / 2");

        assertEquals(5, result);
    }

    @Test
    public void testEvaluate_GivenDivisionBy0_ExpectUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> {
            evaluator.evaluate("10 / 0");
        });
    }

    @Test
    public void testEvaluate_GivenAdditionAndMultiplicationOperators_ExpectOrderOfOperationsRespected() {
        int result = evaluator.evaluate("10 + 2 * 6");

        assertEquals(22, result);
    }

    @Test
    public void testEvaluate_GivenAdditionAndMultiplicationOperators2_ExpectOrderOfOperationsRespected() {
        int result = evaluator.evaluate("100 * 2 + 12");

        assertEquals(212, result);
    }

    @Test
    public void testEvaluate_GivenSubtractionAndMultiplicationOperators_ExpectOrderOfOperationsRespected() {
        int result = evaluator.evaluate("10 - 2 * 6");

        assertEquals(-2, result);
    }

    @Test
    public void testEvaluate_GivenAllOperators_ExpectOrderOfOperationsRespected() {
        int result = evaluator.evaluate("5 * 10 - 2 * 6 + 100");

        assertEquals(138, result);
    }
}

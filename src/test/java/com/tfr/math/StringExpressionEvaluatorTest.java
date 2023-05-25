package com.tfr.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringExpressionEvaluatorTest {

    @Test
    public void testEvaluate_GivenAddition_ExpectSum() {
        Double result = StringExpressionEvaluator.evaluate("10 + 2");

        assertEquals(12, result);
    }

    @Test
    public void testEvaluate_GivenAdditionWithDecimal_ExpectSum() {
        Double result = StringExpressionEvaluator.evaluate("10.5 + 2.1");

        assertEquals(12.6, result);
    }

    @Test
    public void testEvaluate_GivenSubtraction_ExpectDifference() {
        Double result = StringExpressionEvaluator.evaluate("10 - 2");

        assertEquals(8, result);
    }

    @Test
    public void testEvaluate_GivenSubtractionWithDecimal_ExpectDifference() {
        Double result = StringExpressionEvaluator.evaluate("10.2 - 2.1");

        assertEquals(8.1, result);
    }

    @Test
    public void testEvaluate_GivenMultiplication_ExpectProduct() {
        Double result = StringExpressionEvaluator.evaluate("10 * 2");

        assertEquals(20, result);
    }

    @Test
    public void testEvaluate_GivenMultiplicationWithDecimal_ExpectProduct() {
        Double result = StringExpressionEvaluator.evaluate("10.5 * 2");

        assertEquals(21, result);
    }

    @Test
    public void testEvaluate_GivenDivision_ExpectFraction() {
        Double result = StringExpressionEvaluator.evaluate("10 / 2");

        assertEquals(5, result);
    }

    @Test
    public void testEvaluate_GivenDivisionWithDecimal_ExpectFraction() {
        Double result = StringExpressionEvaluator.evaluate("10 / 2.5");

        assertEquals(4, result);
    }

    @Test
    public void testEvaluate_GivenAdditionAndMultiplicationOperators_ExpectOrderOfOperationsRespected() {
        Double result = StringExpressionEvaluator.evaluate("10 + 2 * 6");

        assertEquals(22, result);
    }

    @Test
    public void testEvaluate_GivenAdditionAndMultiplicationOperators2_ExpectOrderOfOperationsRespected() {
        Double result = StringExpressionEvaluator.evaluate("100 * 2 + 12");

        assertEquals(212, result);
    }

    @Test
    public void testEvaluate_GivenSubtractionAndMultiplicationOperators_ExpectOrderOfOperationsRespected() {
        Double result = StringExpressionEvaluator.evaluate("10 - 2 * 6");

        assertEquals(-2, result);
    }

    @Test
    public void testEvaluate_GivenParenthesis_ExpectOrderOfOperationsRespected() {
        Double result = StringExpressionEvaluator.evaluate("100 * ( 2 + 12 )");

        assertEquals(1400, result);
    }

    @Test
    public void testEvaluate_GivenParenthesis2_ExpectOrderOfOperationsRespected() {
        Double result = StringExpressionEvaluator.evaluate("100 * ( 2 + 12 ) / 14");

        assertEquals(100, result);
    }
}

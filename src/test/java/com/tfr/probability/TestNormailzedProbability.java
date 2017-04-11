package com.tfr.probability;

import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for com.tfr.probability.NormalizedProbability
 * Created by Erik on 4/11/2017.
 */
public class TestNormailzedProbability {

    private NormalizedProbability<String> normalizedProbability;

    private String a = "A";
    private String b = "B";
    private String c = "C";

    @Before
    public void setUp() {
        normalizedProbability = new NormalizedProbability<>(2);
    }

    @Test
    public void testGetNormalizationFactor_GivenTotalWeight50_ExpectNormalizationFactor2() {
        normalizedProbability.add(20.0, a);
        normalizedProbability.add(30.0, b);
        runTestGetNormalizationFactor(2.0);
    }

    @Test
    public void testGetNormalizationFactor_GivenTotalWeight200_ExpectNormalizationFactorOneHalf() {
        normalizedProbability.add(50.0, a);
        normalizedProbability.add(150.0, b);
        runTestGetNormalizationFactor(0.5);
    }

    @Test
    public void testGet_GivenTwoItemsTotalBelow100Weight_ExpectNormalization() {
        normalizedProbability.add(10.0, a);
        normalizedProbability.add(40.0, b);

        runTestGet(20.0, a, true);
        runTestGet(20.1, b, true);
    }

    @Test
    public void testGet_GivenAddItem_ExpectNormalizationRefresh() {
        normalizedProbability.add(10.0, a);
        normalizedProbability.add(40.0, b);

        runTestGet(20.0, a, true);
        runTestGet(20.1, b, true);

        normalizedProbability.add(150.0, c);

        runTestGet(5.0, a, true);
        runTestGet(5.1, b, true);
        runTestGet(25.0, b, true);
        runTestGet(25.1, c, true);
    }

    @Test
    public void testGet_GivenAddItemtoTotal100Weight_ExpectNormalizationTrueThenFalse() {
        normalizedProbability.add(10.0, a);
        normalizedProbability.add(40.0, b);

        runTestGet(20.0, a, true);
        runTestGet(20.1, b, true);

        normalizedProbability.add(50.0, c);

        runTestGet(10.0, a, false);
        runTestGet(10.1, b, false);
        runTestGet(50.0, b, false);
        runTestGet(50.1, c, false);
    }

    @Test
    public void testGet_GivenTwoItemsTotal100Weight_ExpectNoNormalization() {
        normalizedProbability.add(20.0, a);
        normalizedProbability.add(80.0, b);

        runTestGet(20.0, a, false);
        runTestGet(20.1, b, false);
    }

    @Test (expected = InvalidParameterException.class)
    public void testGet_GivenParameterBelowRange_ExpectInvalidParameterException() {
        normalizedProbability.get(-0.01);
    }

    @Test (expected = InvalidParameterException.class)
    public void testGet_GivenParameterAboveRange_ExpectInvalidParameterException() {
        normalizedProbability.get(100.01);
    }

    private void runTestGet(Double roll, String expectedOutput, boolean isNormalized) {
        String result = normalizedProbability.get(roll);
        boolean isNormalizedResult = normalizedProbability.isNormalized();
        assertEquals(String.format("Expected get to return %s but returned %s", expectedOutput, result), expectedOutput, result);
        assertEquals(String.format("Expected isNormalized = %s but was %s", isNormalized, isNormalizedResult), isNormalized, isNormalizedResult);
    }

    private void runTestGetNormalizationFactor(Double expected) {
        Double actual = normalizedProbability.getNormalizationFactor();
        assertEquals(String.format("Expected getNormalizationFactor to return %s but got %s", expected, actual), expected, actual);
    }

}

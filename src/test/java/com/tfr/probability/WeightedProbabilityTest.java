package com.tfr.probability;

import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class WeightedProbabilityTest {

    private WeightedProbability<String> weightedProbability;

    private String a = "A";
    private String b = "B";
    private String c = "C";
    private String d = "D";

    @Before
    public void setUp() {
        weightedProbability = new WeightedProbability<>();
    }

    @Test
    public void testGet_ExpectResultFromAddedOutcomes() {
        weightedProbability.addOutcome(500, a);
        weightedProbability.addOutcome(1000, b);

        String result = weightedProbability.get();

        if (!result.equals(a) && !result.equals(b)) {
            fail("Expected A or B to be returned, but got " + result);
        }
    }

    @Test
    public void testGet_ExpectResultsToBeProportionalToWeight() {
        weightedProbability.addOutcome(500, a);
        weightedProbability.addOutcome(1000, b);

        int outcomesA = 0;
        int outcomesB = 0;

        for (int i = 0; i < 10000; i++) {
            String result = weightedProbability.get();

            if (result.equals(a)) {
                outcomesA++;
            } else {
                outcomesB++;
            }
        }

        // there is always a chance that this could fail, it's not exact
        assertEquals(String.format("Expected A or B to be approximately 1:2, but got %s:%s", outcomesA, outcomesB), outcomesA*2, outcomesB, 500);
    }

    @Test (expected = InvalidParameterException.class)
    public void testGet_GivenWeightBelowRange_ExpectInvalidParameterException() {
        weightedProbability.addOutcome(-2.0, a);
    }
}

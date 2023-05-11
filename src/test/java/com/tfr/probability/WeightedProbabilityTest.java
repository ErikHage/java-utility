package com.tfr.probability;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.security.InvalidParameterException;
import java.util.Random;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WeightedProbabilityTest {

    private final String a = "A";
    private final String b = "B";

    private AutoCloseable closeable;
    @Mock
    private Random random;

    @Before
    public void beforeEach() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @After
    public void afterEach() throws Exception {
        closeable.close();
    }

    @Test
    public void testGet_ExpectResultFromAddedOutcomes() {
        WeightedProbability<String> weightedProbability = new WeightedProbability<>();

        weightedProbability.addOutcome(500, a);
        weightedProbability.addOutcome(1000, b);

        String result = weightedProbability.get();

        if (!result.equals(a) && !result.equals(b)) {
            fail("Expected A or B to be returned, but got " + result);
        }
    }

    @Test
    public void testGet_GivenRandomRollsBelow500_ExpectOutcomeA() {
        when(random.nextDouble()).thenReturn(400.0/1500.0);

        WeightedProbability<String> weightedProbability = new WeightedProbability<>(random);

        weightedProbability.addOutcome(500, a);
        weightedProbability.addOutcome(1000, b);

        String result = weightedProbability.get();

        verify(random, times(1)).nextDouble();

        assertEquals(String.format("Expected %s, but got %s", a, result), a, result);
    }

    @Test
    public void testGet_GivenRandomRollsAbove500_ExpectOutcomeB() {
        when(random.nextDouble()).thenReturn(750.0/1500.0);

        WeightedProbability<String> weightedProbability = new WeightedProbability<>(random);

        weightedProbability.addOutcome(500, a);
        weightedProbability.addOutcome(1000, b);

        String result = weightedProbability.get();

        verify(random, times(1)).nextDouble();

        assertEquals(String.format("Expected %s, but got %s", b, result), b, result);
    }

    @Test (expected = InvalidParameterException.class)
    public void testGet_GivenWeightBelowRange_ExpectInvalidParameterException() {
        WeightedProbability<String> weightedProbability = new WeightedProbability<>();

        weightedProbability.addOutcome(-2.0, a);
    }
}

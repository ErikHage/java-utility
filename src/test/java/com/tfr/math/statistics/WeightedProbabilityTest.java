package com.tfr.math.statistics;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.security.InvalidParameterException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WeightedProbabilityTest {

    private final String a = "A";
    private final String b = "B";

    private AutoCloseable closeable;
    @Mock
    private Random random;

    @BeforeEach
    public void beforeEach() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
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

        assertEquals(a, result, String.format("Expected %s, but got %s", a, result));
    }

    @Test
    public void testGet_GivenRandomRollsAbove500_ExpectOutcomeB() {
        when(random.nextDouble()).thenReturn(750.0/1500.0);

        WeightedProbability<String> weightedProbability = new WeightedProbability<>(random);

        weightedProbability.addOutcome(500, a);
        weightedProbability.addOutcome(1000, b);

        String result = weightedProbability.get();

        verify(random, times(1)).nextDouble();

        assertEquals(b, result, String.format("Expected %s, but got %s", b, result));
    }

    @Test
    public void testGet_GivenWeightBelowRange_ExpectInvalidParameterException() {
        WeightedProbability<String> weightedProbability = new WeightedProbability<>();

        assertThrows(InvalidParameterException.class, () -> {
            weightedProbability.addOutcome(-2.0, a);
        });
    }
}

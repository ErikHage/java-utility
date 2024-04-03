package com.tfr.math.probability;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ProbabilityTest {

    @Test
    void shouldCalculateTheProbability() {
        assertEquals(BigDecimal.valueOf(0.1), Probability.probability(10, 90));
    }

    @Test
    void shouldCalculateTheMean() {
        Double mean = Probability.mean(1.0, 2.0, 3.0, 4.0, 5.0);

        assertEquals(3.0, mean);
    }

    @Test
    void shouldCalculateTheMeanOfASetOfOne() {
        Double mean = Probability.mean(3.0);

        assertEquals(3.0, mean);
    }

    @Test
    void shouldCalculateTheMeanFroNoNumbers() {
        Double mean = Probability.mean();

        assertNull(mean);
    }

    @Test
    void shouldCalculateTheMedianForAnOddSet() {
        Double median = Probability.median(1.0, 2.0, 5.0, 3.0, 4.0);

        assertEquals(3.0, median);
    }

    @Test
    void shouldCalculateTheMedianForAnEvenSet() {
        Double median = Probability.median(1.0, 2.0, 3.0, 4.0);

        assertEquals(2.5, median);
    }

    @Test
    void shouldCalculateTheMedianOfASetOfOne() {
        Double mean = Probability.median(3.0);

        assertEquals(3.0, mean);
    }

    @Test
    void shouldReturnNullMedianForNoNumbers() {
        Double mean = Probability.median();

        assertNull(mean);
    }

    @Test
    void shouldCalculateMode() {
        Double mode = Probability.mode(1.0, 2.0, 2.0, 3.0);

        assertEquals(2.0, mode);
    }

    @Test
    void shouldCalculateModeOfASetOfOne() {
        Double mode = Probability.mode(1.0);

        assertEquals(1.0, mode);
    }

    @Test
    void shouldReturnNullModeForNoNumbers() {
        Double mode = Probability.mode();

        assertNull(mode);
    }
}

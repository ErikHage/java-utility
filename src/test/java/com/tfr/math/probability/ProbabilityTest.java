package com.tfr.math.probability;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProbabilityTest {

    @Test
    void shouldCalculateTheProbability() {
        assertEquals(BigDecimal.valueOf(0.1), Probability.probability(10, 90));
    }

    @Test
    void shouldCalculateTheMean() {
        double mean = Probability.mean(1.0, 2.0, 3.0, 4.0, 5.0);

        assertEquals(3.0, mean);
    }

    @Test
    void shouldCalculateTheMeanOfASetOfOne() {
        double mean = Probability.mean(3.0);

        assertEquals(3.0, mean);
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
        double mean = Probability.median(3.0);

        assertEquals(3.0, mean);
    }
}

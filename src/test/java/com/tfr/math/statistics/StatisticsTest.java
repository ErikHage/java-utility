package com.tfr.math.statistics;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StatisticsTest {

    @Test
    void shouldCalculateTheProbability() {
        assertEquals(BigDecimal.valueOf(0.1), Statistics.probability(10, 90));
    }

    @Test
    void shouldCalculateTheMean() {
        Double mean = Statistics.mean(1.0, 2.0, 3.0, 4.0, 5.0);

        assertEquals(3.0, mean);
    }

    @Test
    void shouldCalculateTheMeanOfASetOfOne() {
        Double mean = Statistics.mean(3.0);

        assertEquals(3.0, mean);
    }

    @Test
    void shouldCalculateTheMeanFroNoNumbers() {
        Double mean = Statistics.mean();

        assertNull(mean);
    }

    @Test
    void shouldCalculateTheMedianForAnOddSet() {
        Double median = Statistics.median(1.0, 2.0, 5.0, 3.0, 4.0);

        assertEquals(3.0, median);
    }

    @Test
    void shouldCalculateTheMedianForAnEvenSet() {
        Double median = Statistics.median(1.0, 2.0, 3.0, 4.0);

        assertEquals(2.5, median);
    }

    @Test
    void shouldCalculateTheMedianOfASetOfOne() {
        Double mean = Statistics.median(3.0);

        assertEquals(3.0, mean);
    }

    @Test
    void shouldReturnNullMedianForNoNumbers() {
        Double mean = Statistics.median();

        assertNull(mean);
    }

    @Test
    void shouldCalculateMode() {
        Double mode = Statistics.mode(1.0, 1.0, 2.0, 3.0, 2.0, 1.0);

        assertEquals(1.0, mode);
    }

    @Test
    void shouldCalculateModeOfASetOfOne() {
        Double mode = Statistics.mode(1.0);

        assertEquals(1.0, mode);
    }

    @Test
    void shouldReturnNullModeForNoNumbers() {
        Double mode = Statistics.mode();

        assertNull(mode);
    }
}

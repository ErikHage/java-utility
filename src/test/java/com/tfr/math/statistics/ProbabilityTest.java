package com.tfr.math.statistics;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProbabilityTest {

    @Test
    void shouldCalculateTheProbability() {
        assertEquals(BigDecimal.valueOf(0.1), Probability.probability(10, 90));
    }
}

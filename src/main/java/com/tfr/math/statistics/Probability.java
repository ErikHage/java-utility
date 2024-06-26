package com.tfr.math.statistics;

import java.math.BigDecimal;

public class Probability {
    public static BigDecimal probability(int numberOfFavorableOutcomes, int numberOfUnfavorableOutcomes) {
        return BigDecimal.valueOf((double) numberOfFavorableOutcomes / (numberOfFavorableOutcomes + numberOfUnfavorableOutcomes));
    }
}

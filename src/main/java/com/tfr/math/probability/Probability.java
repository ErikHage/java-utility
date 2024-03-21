package com.tfr.math.probability;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;

public class Probability {

    public static BigDecimal probability(int numberOfFavorableOutcomes, int numberOfUnfavorableOutcomes) {
        return BigDecimal.valueOf((double) numberOfFavorableOutcomes / (numberOfFavorableOutcomes + numberOfUnfavorableOutcomes));
    }

    public static double mean(double... nums) {
        double total = 0;
        for (double num : nums) {
            total += num;
        }

        return total / nums.length;
    }
}

package com.tfr.math.probability;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

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

    public static Double median(Double... nums) {
        if (nums.length == 0) {
            return null;
        }

        Arrays.sort(nums);
        if (nums.length % 2 == 1) {
            return nums[nums.length/2];
        } else {
            Double num1 = nums[(nums.length - 1) / 2];
            Double num2 = nums[(nums.length - 1) / 2 + 1];
            return mean(num1, num2);
        }
    }

    public static double mode(double... nums) {
        Map<Double, Integer> counts = new HashMap<>();

        for (double num : nums) {
            counts.merge(num, 1, Integer::sum);
        }

        Entry<Double, Integer> highestCount = null;
        for (Entry<Double, Integer> count: counts.entrySet()) {
            if (highestCount == null) {
                highestCount = count;
            } else if (highestCount.getValue() < count.getValue()) {
                highestCount = count;
            }
        }

        return highestCount.getValue();
    }
}

package com.tfr.math.statistics;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Statistics {

    public static Double mean(double... nums) {
        if (nums.length == 0) {
            return null;
        }

        double total = 0;
        for (double num : nums) {
            total += num;
        }

        return total / nums.length;
    }

    public static Double median(double... nums) {
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

    public static Double mode(double... nums) {
        if (nums.length == 0) {
            return null;
        }

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

        return highestCount.getKey();
    }

    public static Double range(double... nums) {
        if (nums.length == 0) {
            return null;
        }

        double highest = nums[0];
        double lowest = nums[0];

        for (double num: nums) {
            if (num > highest) {
                highest = num;
            }
            if (num < lowest) {
                lowest = num;
            }
        }

        return highest - lowest;
    }
}

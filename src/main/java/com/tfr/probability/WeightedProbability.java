package com.tfr.probability;

import java.security.InvalidParameterException;
import java.util.*;

/**
 *
 */
public class WeightedProbability<T> {
    private final NavigableMap<Double, T> mappedOutcomes;
    private final Random random;
    private double totalWeight;

    /**
     * Instantiate a new WeightedProbability
     *
     */
    public WeightedProbability() {
        this.mappedOutcomes = new TreeMap<Double, T>();
        this.random = new Random();
        this.totalWeight = 0;
    }

    public void addOutcome(double weight, T outcome) {
        if (weight <= 0) {
            throw new InvalidParameterException("Weight must be greater than 0");
        }

        totalWeight += weight;

        mappedOutcomes.put(totalWeight, outcome);
    }

    public T get() {
        double roll = random.nextDouble() * totalWeight;
        return mappedOutcomes.higherEntry(roll).getValue();
    }
}
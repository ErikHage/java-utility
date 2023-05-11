package com.tfr.probability;

import java.security.InvalidParameterException;
import java.util.*;

/**
 *  Build a collection of weighted outcomes of type T. Generates responses based on the total weight of all added
 *  outcomes.
 */
public class WeightedProbability<T> {
    private final NavigableMap<Double, T> mappedOutcomes;
    private final Random random;
    private double totalWeight;

    /**
     * Create a new instance of WeightedProbability using the default: java.util.Random
     */
    public WeightedProbability() {
        this(new Random());
    }

    /**
     * Create a new instance of WeightedProbability
     */
    public WeightedProbability(Random random) {
        this.mappedOutcomes = new TreeMap<Double, T>();
        this.random = random;
        this.totalWeight = 0;
    }

    /**
     * Add a new outcome to the possible outcomes with an associated weight
     *
     * @param weight - chance for the corresponding value to be returned
     * @param outcome - value to be returned
     */
    public void addOutcome(double weight, T outcome) {
        if (weight <= 0) {
            throw new InvalidParameterException("Weight must be greater than 0");
        }

        totalWeight += weight;

        mappedOutcomes.put(totalWeight, outcome);
    }

    /**
     * Returns one of the added items with a probability based on the normalized weight provided
     *
     * @return - a response from the added items
     */
    public T get() {
        double roll = random.nextDouble() * totalWeight;
        return mappedOutcomes.higherEntry(roll).getValue();
    }
}
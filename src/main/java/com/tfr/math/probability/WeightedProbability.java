package com.tfr.math.probability;

import java.security.InvalidParameterException;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

/**
 *  Build a collection of weighted outcomes of type T. Generates responses based on the total weight of all added
 *  outcomes.
 */
public class WeightedProbability<T> {
    private final NavigableMap<Double, T> mappedOutcomes;
    private final Random random;
    private double totalWeight;

    public WeightedProbability() {
        this(new Random());
    }

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
     * Returns one of the added outcomes with a probability based on the weights associated with each outcome
     *
     * @return T - a response from the added items
     */
    public T get() {
        double roll = random.nextDouble() * totalWeight;
        return mappedOutcomes.higherEntry(roll).getValue();
    }
}
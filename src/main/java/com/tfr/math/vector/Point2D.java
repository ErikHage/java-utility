package com.tfr.math.vector;

public record Point2D(double x, double y) implements Point {

    public double z() {
        return 0;
    }
}

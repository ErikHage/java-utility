package com.tfr.math.point;

public record Point2D(double x, double y) implements Point {

    public double z() {
        return 0;
    }
}

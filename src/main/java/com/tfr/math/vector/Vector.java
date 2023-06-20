package com.tfr.math.vector;

public class Vector {

    private final double x;
    private final double y;
    private final double magnitude;
    private final double angleDegrees;
    private final double angleRadians;

    private Vector(double x, double y, double magnitude, double angleDegrees, double angleRadians) {
        this.x = x;
        this.y = y;
        this.magnitude = magnitude;
        this.angleDegrees = angleDegrees;
        this.angleRadians = angleRadians;
    }

    public static Vector fromComponents(double x, double y) {
        double magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double angleRadians = Math.atan(y/x);
        double angleDegrees = angleRadians * (180/Math.PI);

        return new Vector(x, y, magnitude, angleDegrees, angleRadians);
    }

    public static Vector fromMagnitudeAndDirection(double magnitude, double angle, AngleUnits angleUnits) {
        double angleRadians;
        double angleDegrees;
        if (angleUnits == AngleUnits.DEGREES) {
            angleDegrees = angle;
            angleRadians = angle * (Math.PI/180);
        } else {
            angleRadians = angle;
            angleDegrees = angle * (180/Math.PI);
        }

        double x = magnitude * Math.cos(angleRadians);
        double y = magnitude * Math.sin(angleRadians);

        return new Vector(x, y, magnitude, angleDegrees, angleRadians);
    }

    public double x() {
        return x;
    }

    public double y() {
        return y;
    }

    public Double magnitude() {
        return magnitude;
    }

    public Double angle(AngleUnits angleUnit) {
        if (angleUnit == AngleUnits.DEGREES) {
            return angleDegrees;
        }
        return angleRadians;
    }
}

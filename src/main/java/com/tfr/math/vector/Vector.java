package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import com.tfr.math.trig.TrigMath;

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
        double magnitude = TrigMath.getHypotenuse(x, y);

        double angleRadians = Math.atan(Math.abs(y)/Math.abs(x));
        if (y > 0 && x < 0) {
            // quadrant 2
            angleRadians += (Math.PI / 2);
        } else if (y < 0 && x < 0) {
            // quadrant 3
            angleRadians += Math.PI;
        } else if (y < 0 && x > 0) {
            // quadrant 4
            angleRadians +=  (3 * Math.PI / 2);
        }

        double angleDegrees = TrigMath.toDegrees(angleRadians);

        return new Vector(x, y, magnitude, angleDegrees, angleRadians);
    }

    public static Vector fromMagnitudeAndDirection(double magnitude, double angle, AngleUnits angleUnits) {
        double angleRadians;
        double angleDegrees;
        if (angleUnits == AngleUnits.DEGREES) {
            angleDegrees = angle;
            angleRadians = TrigMath.toRadians(angle);
        } else {
            angleRadians = angle;
            angleDegrees = TrigMath.toDegrees(angle);
        }

        double x = TrigMath.getXProjection(magnitude, angleRadians);
        double y = TrigMath.getYProjection(magnitude, angleRadians);

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

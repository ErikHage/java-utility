package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import com.tfr.math.trig.TrigMath;

public class VectorXY {

    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;

    public VectorXY(double x1, double x2, double y1, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public double direction(AngleUnits angleUnits) {
        double angle = Math.atan((y2 - y1)/(x2 - x1));

        if (angleUnits == AngleUnits.DEGREES) {
            return TrigMath.toDegrees(angle);
        }
        return angle;
    }
}

package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import com.tfr.math.trig.TrigMath;

public class VectorXY {

    private final Point point1;
    private final Point point2;
    private final VectorDirection vectorDirection;

    public VectorXY(double x1, double x2, double y1, double y2) {
        this.point1 = new Point(x1, y1);
        this.point2 = new Point(x2, y2);
        this.vectorDirection = VectorMath.getVectorDirection(point1, point2);
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(point2.x() - point1.x(), 2) + Math.pow(point2.y() - point1.y(), 2));
    }

    public double direction(AngleUnits angleUnits) {
        double xDiff = Math.abs(point2.x() - point1.x());
        double yDiff = Math.abs(point2.y() - point1.y());
        double angle = Math.atan(yDiff / xDiff);

        double adjustedAngle = VectorMath.adjustAngle(angle, vectorDirection);

        if (angleUnits == AngleUnits.DEGREES) {
            return TrigMath.toDegrees(adjustedAngle);
        }
        return adjustedAngle;
    }
}

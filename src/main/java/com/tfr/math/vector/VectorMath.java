package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import com.tfr.math.trig.TrigMath;
import com.tfr.math.vector.force.ForceXY;

public class VectorMath {

    public static ForceXY calculateResultantForce(ForceXY... vectors) {
        double xComponents = 0.0;
        double yComponents = 0.0;

        for (ForceXY v: vectors) {
            xComponents += v.xProjection();
            yComponents += v.yProjection();
        }

        return new ForceXY(xComponents, yComponents);
    }

    public static double magnitude(Vector vector) {
        return Math.sqrt(
                Math.pow(vector.xProjection(), 2)
                + Math.pow(vector.yProjection(), 2)
                + Math.pow(vector.zProjection(), 2)
        );
    }

    public static double dotProduct(Vector v1, Vector v2) {
        return (v1.xProjection() * v2.xProjection())
                + (v1.yProjection() * v2.yProjection())
                + (v1.zProjection() * v2.zProjection());
    }

    public static Vector crossProduct(Vector v1, Vector v2) {
        double x = (v1.yProjection() * v2.zProjection()) - (v1.zProjection() * v2.yProjection());
        double y = (v1.xProjection() * v2.zProjection()) - (v1.zProjection() * v2.xProjection());
        double z = (v1.xProjection() * v2.yProjection()) - (v1.yProjection() * v2.xProjection());

        y = y * -1;

        return new VectorXYZ(x, y, z);
    }

    public static double alpha(Vector vector, AngleUnits angleUnits) {
        double adjacent = vector.xProjection();
        return directionAngle(magnitude(vector), adjacent, angleUnits);
    }

    public static double beta(Vector vector, AngleUnits angleUnits) {
        double adjacent = vector.yProjection();
        return directionAngle(magnitude(vector), adjacent, angleUnits);
    }

    public static double gamma(Vector vector, AngleUnits angleUnits) {
        double adjacent = vector.zProjection();
        return directionAngle(magnitude(vector), adjacent, angleUnits);
    }

    private static double directionAngle(double hypotenuse, double adjacent, AngleUnits angleUnits) {
        double angleRadians = Math.acos(adjacent/hypotenuse);

        if (angleUnits == AngleUnits.DEGREES) {
            return TrigMath.toDegrees(angleRadians);
        }
        return angleRadians;
    }
}

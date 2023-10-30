package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import com.tfr.math.trig.TrigMath;
import com.tfr.math.vector.force.ForceXY;

/**
 * A collection of mathematical operations for Vectors
 */
public class VectorMath {

    /**
     * Calculate the resultant force of two or more two-dimensional forces.
     * @param forceOne - the first force in the resultant
     * @param forces - an array of forces to combine with the first into a resultant
     * @return - ForceXY - the resultant of the given forces
     */
    public static ForceXY calculateResultantForce(ForceXY forceOne, ForceXY... forces) {
        double xComponents = forceOne.xProjection();
        double yComponents = forceOne.yProjection();

        for (ForceXY v: forces) {
            xComponents += v.xProjection();
            yComponents += v.yProjection();
        }

        return new ForceXY(xComponents, yComponents);
    }

    /**
     * Calculate the magnitude of a given vector
     * @param vector - a 2 or 3 dimensional vector
     * @return - double - the magnitude of the given vector
     */
    public static double magnitude(Vector vector) {
        return Math.sqrt(
                Math.pow(vector.xProjection(), 2)
                + Math.pow(vector.yProjection(), 2)
                + Math.pow(vector.zProjection(), 2)
        );
    }

    /**
     * Perform the dot product between the two given vectors.
     * @param v1 - first vector
     * @param v2 - second vector
     * @return - double - the result of the dot product of the given vectors
     */
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

    public static double angle(Vector v1, Vector v2) {
        double dotProd = dotProduct(v1, v2);
        double magnitude1 = magnitude(v1);
        double magnitude2 = magnitude(v2);
        double magnitudeProd = magnitude1 * magnitude2;

        double ratio = dotProd / magnitudeProd;

        // correct rounding errors bc of floating point inaccuracy
        if (ratio < -1 || ratio > 1) {
            ratio = ratio / Math.abs(ratio);
        }

        return Math.acos(ratio);
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

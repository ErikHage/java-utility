package com.tfr.math.trig;

public class TrigMath {

    /**
     * Convert angle in degrees to angle in radians
     * @param degrees - angle in degrees
     * @return angle in radians
     */
    public static double toRadians(double degrees) {
        return degrees * (Math.PI/180);
    }

    /**
     * Convert angle in radian to angle in degrees
     * @param radians - angle in radians
     * @return angle in degrees
     */
    public static double toDegrees(double radians) {
        return radians * (180/Math.PI);
    }

    /**
     * Calculate the projection of the vector defined by the provided magnitude and
     * angle from the x-axis
     * @param magnitude - the magnitude of the vector
     * @param angleInRadians - the direction of the vector in radians
     * @return x projection in same length units as magnitude
     */
    public static double getXProjection(double magnitude, double angleInRadians) {
        return magnitude * Math.cos(angleInRadians);
    }

    /**
     * Calculate the projection of the vector defined by the provided magnitude and
     * angle from the x-axis
     * @param magnitude - the magnitude of the vector
     * @param angleInRadians - the direction of the vector in radians
     * @return y projection in same length units as magnitude
     */
    public static double getYProjection(double magnitude, double angleInRadians) {
        return magnitude * Math.sin(angleInRadians);
    }

    /**
     * Find the hypotenuse of a triangle given the other two sides
     * @param x - side x
     * @param y - side y
     * @return length of the hypotenuse in the same units as the provided sides
     */
    public static double getHypotenuse(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}

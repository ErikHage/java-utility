package com.tfr.math.trig;

public class TrigMath {

    public static double toRadians(double degrees) {
        return degrees * (Math.PI/180);
    }

    public static double toDegrees(double radians) {
        return radians * (180/Math.PI);
    }

    public static double getXProjection(double magnitude, double angleInRadians) {
        return magnitude * Math.cos(angleInRadians);
    }

    public static double getYProjection(double magnitude, double angleInRadians) {
        return magnitude * Math.sin(angleInRadians);
    }

    public static double getHypotenuse(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}

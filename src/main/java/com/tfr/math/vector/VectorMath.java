package com.tfr.math.vector;

public class VectorMath {

    public static ForceXY calculateResultantForce(ForceXY... vectors) {
        double xComponents = 0.0;
        double yComponents = 0.0;

        for (ForceXY v: vectors) {
            xComponents += v.x();
            yComponents += v.y();
        }

        return new ForceXY(xComponents, yComponents);
    }
}

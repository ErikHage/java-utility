package com.tfr.math.vector;

public class VectorMath {

    public static Vector resultant(Vector... vectors) {
        double xComponents = 0.0;
        double yComponents = 0.0;

        for (Vector v: vectors) {
            xComponents += v.x();
            yComponents += v.y();
        }

        return Vector.fromComponents(xComponents, yComponents);
    }
}

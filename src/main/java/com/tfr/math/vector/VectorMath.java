package com.tfr.math.vector;

public class VectorMath {

    public static VectorXY resultant(VectorXY... vectors) {
        double xComponents = 0.0;
        double yComponents = 0.0;

        for (VectorXY v: vectors) {
            xComponents += v.x();
            yComponents += v.y();
        }

        return VectorXY.fromComponents(xComponents, yComponents);
    }
}

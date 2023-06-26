package com.tfr.math.vector;

public class VectorXYZ {

    private final Point3D point1;
    private final Point3D point2;

    public VectorXYZ(double x1, double x2, double y1, double y2, double z1, double z2) {
        this.point1 = new Point3D(x1, y1, z1);
        this.point2 = new Point3D(x2, y2, z2);
    }

    public double magnitude() {
        return Math.sqrt(
                Math.pow(point2.x() - point1.x(), 2)
                + Math.pow(point2.y() - point1.y(), 2)
                + Math.pow(point2.z() - point1.z(), 2)
        );
    }
}

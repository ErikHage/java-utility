package com.tfr.math.vector;

import com.tfr.math.point.Point3D;

public class VectorXYZ implements Vector {

    private final Point3D point1;
    private final Point3D point2;

    /**
     * Create a vector with the given point coordinates
     * @param x1 - x coordinate of vector origin point
     * @param x2 - x coordinate of vector termination point
     * @param y1 - y coordinate of vector origin point
     * @param y2 - y coordinate of vector termination point
     * @param z1 - z coordinate of vector origin point
     * @param z2 - z coordinate of vector termination point
     */
    public VectorXYZ(double x1, double x2, double y1, double y2, double z1, double z2) {
        this.point1 = new Point3D(x1, y1, z1);
        this.point2 = new Point3D(x2, y2, z2);
    }

    /**
     * Create a vector starting at the origin with the given termination point
     * @param x - x coordinate of vector termination point
     * @param y - y coordinate of vector termination point
     * @param z - z coordinate of vector termination point
     */
    public VectorXYZ(double x, double y, double z) {
        this.point1 = new Point3D(0, 0, 0);
        this.point2 = new Point3D(x, y, z);
    }

    /**
     * The origination point of the vector
     * @return - Point3D - a point in three-dimensional space
     */
    @Override
    public Point3D point1() {
        return point1;
    }

    /**
     * The termination point of the vector
     * @return - Point3D - a point in three-dimensional space
     */
    @Override
    public Point3D point2() {
        return point2;
    }

    /**
     * Calculate the x projection of the vector
     * @return - double - the projection of the vector on the x-axis
     */
    @Override
    public double xProjection() {
        return point2.x() - point1.x();
    }

    @Override
    public double yProjection() {
        return point2.y() - point1.y();
    }

    @Override
    public double zProjection() {
        return point2.z() - point1.z();
    }

    @Override
    public String toString() {
        return "VectorXYZ{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                '}';
    }
}

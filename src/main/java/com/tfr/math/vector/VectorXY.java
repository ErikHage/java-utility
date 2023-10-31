package com.tfr.math.vector;

import com.tfr.math.point.Point2D;

public class VectorXY implements Vector {

    private final Point2D point1;
    private final Point2D point2;

    /**
     * Create a vector with the given point coordinates
     * @param x1 - x coordinate of vector origin point
     * @param x2 - x coordinate of vector termination point
     * @param y1 - y coordinate of vector origin point
     * @param y2 - y coordinate of vector termination point
     */
    public VectorXY(double x1, double x2, double y1, double y2) {
        this.point1 = new Point2D(x1, y1);
        this.point2 = new Point2D(x2, y2);
    }

    public VectorXY(double x, double y) {
        this.point1 = new Point2D(0, 0);
        this.point2 = new Point2D(x, y);
    }

    /**
     * The origination point of the vector
     * @return - Point2D - a point in two-dimensional space
     */
    @Override
    public Point2D point1() {
        return point1;
    }

    /**
     * The termination point of the vector
     * @return - Point2D - a point in two-dimensional space
     */
    @Override
    public Point2D point2() {
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

    /**
     * Calculate the y projection of the vector
     * @return - double - the projection of the vector on the y-axis
     */
    @Override
    public double yProjection() {
        return point2.y() - point1.y();
    }

    /**
     * Calculate the z projection of the vector (0 for 2D Vectors)
     * @return - double - the projection of the vector on the z-axis
     */
    @Override
    public double zProjection() {
        return 0;
    }

    @Override
    public String toString() {
        return "VectorXY{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                '}';
    }
}

package com.tfr.math.vector;

import com.tfr.math.point.Point2D;

public class VectorXY implements Vector {

    private final Point2D point1;
    private final Point2D point2;

    public VectorXY(double x1, double x2, double y1, double y2) {
        this.point1 = new Point2D(x1, y1);
        this.point2 = new Point2D(x2, y2);
    }

    public VectorXY(double x, double y) {
        this.point1 = new Point2D(0, 0);
        this.point2 = new Point2D(x, y);
    }

    @Override
    public Point2D point1() {
        return point1;
    }

    @Override
    public Point2D point2() {
        return point2;
    }

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

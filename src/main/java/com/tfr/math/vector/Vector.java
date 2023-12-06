package com.tfr.math.vector;

import com.tfr.math.point.Point;

public interface Vector {

    /**
     * The starting point of the vector
     * @return Point
     */
    Point point1();

    Point point2();

    double xProjection();

    double yProjection();

    double zProjection();
}

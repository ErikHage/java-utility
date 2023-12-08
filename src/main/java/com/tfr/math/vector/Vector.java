package com.tfr.math.vector;

import com.tfr.math.point.Point;

public interface Vector {

    /**
     * The starting point of the vector
     * @return Point
     */
    Point point1();

    /**
     * The ending point of the vector
     * @return Point
     */
    Point point2();

    /**
     * The x projection of the vector
     * @return - double - the projection of the vector on the x-axis
     */
    double xProjection();

    double yProjection();

    double zProjection();
}

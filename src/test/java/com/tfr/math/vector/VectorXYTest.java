package com.tfr.math.vector;

import com.tfr.math.point.Point2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorXYTest {

    @Test
    public void testPoint1_ExpectPoint1() {
        Vector v = new VectorXY(0,1,0,2);
        assertEquals(new Point2D(0,0), v.point1());
    }

    @Test
    public void testPoint1_ExpectPoint1DefaultsToZero() {
        Vector v = new VectorXY(1,2);
        assertEquals(new Point2D(0,0), v.point1());
    }

    @Test
    public void testPoint2_ExpectPoint2() {
        Vector v = new VectorXY(0,1,0,2);
        assertEquals(new Point2D(1,2), v.point2());
    }

    @Test
    public void testProjections_ExpectCorrectValues() {
        Vector v = new VectorXY(1,3,1,5);
        assertEquals(2, v.xProjection());
        assertEquals(4, v.yProjection());
        assertEquals(0, v.zProjection());
    }

    @Test
    public void testToString() {
        Vector v = new VectorXY(1,1,3,5);
        assertEquals("VectorXY{point1=Point2D[x=1.0, y=3.0], point2=Point2D[x=1.0, y=5.0]}", v.toString());
    }
}

package com.tfr.math.vector;

import com.tfr.math.point.Point3D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorXYZTest {

    @Test
    public void testPoint1_ExpectPoint1() {
        Vector v = new VectorXYZ(0,1,0,2, 0, 3);
        assertEquals(new Point3D(0,0, 0), v.point1());
    }

    @Test
    public void testPoint2_ExpectPoint2() {
        Vector v = new VectorXYZ(0,1,0,2, 0, 3);
        assertEquals(new Point3D(1,2, 3), v.point2());
    }

    @Test
    public void testProjections_ExpectCorrectValues() {
        Vector v = new VectorXYZ(1,3,1,5, 1, 7);
        assertEquals(2, v.xProjection());
        assertEquals(4, v.yProjection());
        assertEquals(6, v.zProjection());
    }

    @Test
    public void testToString() {
        Vector v = new VectorXYZ(1,1,3,5,2,2);
        assertEquals("VectorXYZ{point1=Point3D[x=1.0, y=3.0, z=2.0], point2=Point3D[x=1.0, y=5.0, z=2.0]}", v.toString());
    }
}

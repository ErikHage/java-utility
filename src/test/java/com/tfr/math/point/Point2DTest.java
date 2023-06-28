package com.tfr.math.point;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Point2DTest {

    @Test
    public void testXYZ_Expect120() {
        Point p = new Point2D(1,2);
        assertEquals(1, p.x());
        assertEquals(2, p.y());
        assertEquals(0, p.z());
    }
}

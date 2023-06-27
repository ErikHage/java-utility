package com.tfr.math.point;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Point2DTest {

    @Test
    public void testZ_Expect0() {
        Point p = new Point2D(1,1);
        assertEquals(0, p.z());
    }
}

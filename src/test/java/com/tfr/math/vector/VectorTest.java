package com.tfr.math.vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorTest {

    @Test
    public void testMagnitude_Givenx3y4_Expect5() {
        Vector vector = Vector.fromComponents(3.0,4.0);

        assertEquals(5.0, vector.magnitude());
    }

    @Test
    public void testAngle_Givenx3y4_Expect53ish() {
        Vector vector = Vector.fromComponents(3.0,4.0);

        assertEquals(53.13, vector.angle(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testX_Given5at53ish_Expect34() {
        Vector vector = Vector.fromMagnitudeAndDirection(5.0, 53.13, AngleUnits.DEGREES);

        assertEquals(3.0, vector.x(), 0.001);
        assertEquals(4.0, vector.y(), 0.001);
    }
}

package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ForceXYTest {

    @Test
    public void testMagnitude_Givenx3y4_Expect5() {
        ForceXY vector = ForceXY.fromComponents(3.0,4.0);

        assertEquals(5.0, vector.magnitude());
    }

    @Test
    public void testMagnitude_Givenxneg3yneg4_Expect5() {
        ForceXY vector = ForceXY.fromComponents(-3.0,-4.0);

        assertEquals(5.0, vector.magnitude());
    }

    @Test
    public void testMagnitude_Givenxneg3y4_Expect5() {
        ForceXY vector = ForceXY.fromComponents(-3.0,4.0);

        assertEquals(5.0, vector.magnitude());
    }

    @Test
    public void testMagnitude_Givenx3yneg4_Expect5() {
        ForceXY vector = ForceXY.fromComponents(3.0,-4.0);

        assertEquals(5.0, vector.magnitude());
    }

    @Test
    public void testMagnitude_Givenx0y0_Expect0() {
        ForceXY vector = ForceXY.fromComponents(0,0);

        assertEquals(0, vector.magnitude());
    }

    @Test
    public void testAngle_Givenx3y4_Expect53ish() {
        ForceXY vector = ForceXY.fromComponents(3.0,4.0);

        assertEquals(53.13, vector.angle(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testAngle_Givenxneg3y4_Expect53ish() {
        ForceXY vector = ForceXY.fromComponents(-3.0,4.0);

        assertEquals(143.13, vector.angle(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testAngle_Givenxneg3yneg4_Expect233ish() {
        ForceXY vector = ForceXY.fromComponents(-3.0,-4.0);

        assertEquals(233.13, vector.angle(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testAngle_Givenx3yneg4_Expect233ish() {
        ForceXY vector = ForceXY.fromComponents(3.0,-4.0);

        assertEquals(323.13, vector.angle(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testAngle_Givenx0y0_ExpectNaN() {
        ForceXY vector = ForceXY.fromComponents(0,0);

        assertTrue(Double.isNaN(vector.angle(AngleUnits.DEGREES)));
    }

    @Test
    public void testX_Given5at53ish_Expect34() {
        ForceXY vector = ForceXY.from(5.0, 53.13, AngleUnits.DEGREES);

        assertEquals(3.0, vector.x(), 0.001);
        assertEquals(4.0, vector.y(), 0.001);
    }
}

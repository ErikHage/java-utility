package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForceXYTest {

    @Test
    public void testMagnitude_Givenx3y4_Expect5() {
        ForceXY vector = new ForceXY(3.0,4.0);

        assertEquals(5.0, VectorMath.magnitude(vector));
    }

    @Test
    public void testMagnitude_Givenxneg3yneg4_Expect5() {
        ForceXY vector = new ForceXY(-3.0,-4.0);

        assertEquals(5.0, VectorMath.magnitude(vector));
    }

    @Test
    public void testMagnitude_Givenxneg3y4_Expect5() {
        ForceXY vector = new ForceXY(-3.0,4.0);

        assertEquals(5.0, VectorMath.magnitude(vector));
    }

    @Test
    public void testMagnitude_Givenx3yneg4_Expect5() {
        ForceXY vector = new ForceXY(3.0,-4.0);

        assertEquals(5.0, VectorMath.magnitude(vector));
    }

    @Test
    public void testMagnitude_Givenx0y0_Expect0() {
        ForceXY vector = new ForceXY(0,0);

        assertEquals(0, VectorMath.magnitude(vector));
    }

    @Test
    public void testAngle_Givenx3y4_Expect53ish() {
        ForceXY vector = new ForceXY(3.0,4.0);

        assertEquals(53.13, vector.direction(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testAngle_Givenxneg3y4_Expect53ish() {
        ForceXY vector = new ForceXY(-3.0,4.0);

        System.out.println(vector);

        assertEquals(126.869, vector.direction(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testAngle_Givenxneg3yneg4_Expect233ish() {
        ForceXY vector = new ForceXY(-3.0,-4.0);

        assertEquals(233.13, vector.direction(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testAngle_Givenx3yneg4_Expect233ish() {
        ForceXY vector = new ForceXY(3.0,-4.0);

        assertEquals(306.869, vector.direction(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testAngle_Givenx0y0_ExpectNaN() {
        ForceXY vector = new ForceXY(0,0);

        assertThrows(RuntimeException.class, () -> {
            vector.direction(AngleUnits.DEGREES);
        });
    }
}

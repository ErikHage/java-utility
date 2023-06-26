package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorXYTest {

    @Test
    public void testMagnitude_Givenx3y4_Expect5() {
        VectorXY vector = new VectorXY(0.0,3.0, 0.0,4.0);

        assertEquals(5.0, VectorMath.magnitude(vector));
    }

    @Test
    public void testMagnitude_Givenxneg3y4_Expect5() {
        VectorXY vector = new VectorXY(0.0,-3.0, 0.0,4.0);

        assertEquals(5.0, VectorMath.magnitude(vector));
    }

    @Test
    public void testMagnitude_Givenx3yneg4_Expect5() {
        VectorXY vector = new VectorXY(0.0,3.0, 0.0,-4.0);

        assertEquals(5.0, VectorMath.magnitude(vector));
    }

    @Test
    public void testMagnitude_Givenxneg3yneg4_Expect5() {
        VectorXY vector = new VectorXY(0.0,-3.0, 0.0,-4.0);

        assertEquals(5.0, VectorMath.magnitude(vector));
    }

    @Test
    public void testDirection_Givenx3y3Degrees_Expect45() {
        VectorXY vector = new VectorXY(0.0, 3.0, 0.0, 3.0);

        assertEquals(45.0, vector.direction(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testDirection_Givenxneg3y3Degrees_Expect45() {
        VectorXY vector = new VectorXY(0.0, -3.0, 0.0, 3.0);

        assertEquals(135.0, vector.direction(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testDirection_Givenx3yneg3Degrees_Expect45() {
        VectorXY vector = new VectorXY(0.0, 3.0, 0.0, -3.0);

        assertEquals(315.0, vector.direction(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testDirection_Givenxneg3yneg3Degrees_Expect45() {
        VectorXY vector = new VectorXY(0.0, -3.0, 0.0, -3.0);

        assertEquals(225.0, vector.direction(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testAngle_Givenx3y3Radians_ExpectPiOver4() {
        VectorXY vector = new VectorXY(0.0, 3.0, 0.0, 3.0);

        assertEquals(Math.PI/4, vector.direction(AngleUnits.RADIANS));
    }
}

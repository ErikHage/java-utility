package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorXYTest {

    @Test
    public void testMagnitude_Givenx3y4_Expect5() {
        VectorXY vector = new VectorXY(0.0,3.0, 0.0,4.0);

        assertEquals(5.0, vector.magnitude());
    }

    @Test
    public void testMagnitude_Givenxneg3y4_Expect5() {
        VectorXY vector = new VectorXY(0.0,-3.0, 0.0,4.0);

        assertEquals(5.0, vector.magnitude());
    }

    @Test
    public void testMagnitude_Givenx3yneg4_Expect5() {
        VectorXY vector = new VectorXY(0.0,3.0, 0.0,-4.0);

        assertEquals(5.0, vector.magnitude());
    }

    @Test
    public void testMagnitude_Givenxneg3yneg4_Expect5() {
        VectorXY vector = new VectorXY(0.0,-3.0, 0.0,-4.0);

        assertEquals(5.0, vector.magnitude());
    }

    @Test
    public void testDirection_Givenx3y4Degrees_Expect53ish() {
        VectorXY vector = new VectorXY(0.0, 3.0, 0.0, 4.0);

        assertEquals(53.13, vector.direction(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testAngle_Givenx3y4Radians_ExpectPoint927295ish() {
        VectorXY vector = new VectorXY(0.0, 3.0, 0.0, 4.0);

        assertEquals(0.927295, vector.direction(AngleUnits.RADIANS), 0.000001);
    }
}

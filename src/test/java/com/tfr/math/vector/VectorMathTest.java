package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorMathTest {

    @Test
    public void testResultant_GivenTwoVectors_ExpectCorrectResult() {
        Vector v1 = Vector.fromComponents(0,1);
        Vector v2 = Vector.fromComponents(1,0);

        Vector resultant = VectorMath.resultant(v1, v2);

        assertEquals(1.0, resultant.x());
        assertEquals(1.0, resultant.y());
        assertEquals(Math.sqrt(2.0), resultant.magnitude());
        assertEquals(45.0, resultant.angle(AngleUnits.DEGREES));
    }

    @Test
    public void testResultant_GivenThreeVectors_ExpectCorrectResult() {
        Vector v1 = Vector.fromComponents(0,1);
        Vector v2 = Vector.fromComponents(1,0);
        Vector v3 = Vector.fromComponents(2,3);

        Vector resultant = VectorMath.resultant(v1, v2, v3);

        assertEquals(3.0, resultant.x());
        assertEquals(4.0, resultant.y());
        assertEquals(5.0, resultant.magnitude());
        assertEquals(53.13, resultant.angle(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testResultant_GivenNegativeComponents_ExpectCorrectResult() {
        Vector v1 = Vector.fromComponents(0,-1);
        Vector v2 = Vector.fromComponents(-1,0);

        Vector resultant = VectorMath.resultant(v1, v2);

        assertEquals(-1.0, resultant.x());
        assertEquals(-1.0, resultant.y());
        assertEquals(Math.sqrt(2.0), resultant.magnitude());
        assertEquals(225.0, resultant.angle(AngleUnits.DEGREES));
    }
}

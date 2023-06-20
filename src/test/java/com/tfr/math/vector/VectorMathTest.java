package com.tfr.math.vector;

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
}

package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorMathTest {

    @Test
    public void testCalculateResultantForce_GivenTwoVectors_ExpectCorrectResult() {
        ForceXY v1 = ForceXY.fromComponents(0,1);
        ForceXY v2 = ForceXY.fromComponents(1,0);

        ForceXY resultant = VectorMath.calculateResultantForce(v1, v2);

        assertEquals(1.0, resultant.x());
        assertEquals(1.0, resultant.y());
        assertEquals(Math.sqrt(2.0), resultant.magnitude());
        assertEquals(45.0, resultant.angle(AngleUnits.DEGREES));
    }

    @Test
    public void testCalculateResultantForce_GivenThreeVectors_ExpectCorrectResult() {
        ForceXY v1 = ForceXY.fromComponents(0,1);
        ForceXY v2 = ForceXY.fromComponents(1,0);
        ForceXY v3 = ForceXY.fromComponents(2,3);

        ForceXY resultant = VectorMath.calculateResultantForce(v1, v2, v3);

        assertEquals(3.0, resultant.x());
        assertEquals(4.0, resultant.y());
        assertEquals(5.0, resultant.magnitude());
        assertEquals(53.13, resultant.angle(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testCalculateResultantForce_GivenNegativeComponents_ExpectCorrectResult() {
        ForceXY v1 = ForceXY.fromComponents(0,-1);
        ForceXY v2 = ForceXY.fromComponents(-1,0);

        ForceXY resultant = VectorMath.calculateResultantForce(v1, v2);

        assertEquals(-1.0, resultant.x());
        assertEquals(-1.0, resultant.y());
        assertEquals(Math.sqrt(2.0), resultant.magnitude());
        assertEquals(225.0, resultant.angle(AngleUnits.DEGREES));
    }
}

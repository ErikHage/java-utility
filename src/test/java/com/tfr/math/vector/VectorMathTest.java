package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import com.tfr.math.vector.force.ForceXY;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorMathTest {

    @Test
    public void testCalculateResultantForce_GivenTwoVectors_ExpectCorrectResult() {
        ForceXY v1 = new ForceXY(0,1);
        ForceXY v2 = new ForceXY(1,0);

        ForceXY resultant = VectorMath.calculateResultantForce(v1, v2);

        assertEquals(1.0, resultant.xProjection());
        assertEquals(1.0, resultant.yProjection());
    }

    @Test
    public void testCalculateResultantForce_GivenThreeVectors_ExpectCorrectResult() {
        ForceXY v1 = new ForceXY(0,1);
        ForceXY v2 = new ForceXY(1,0);
        ForceXY v3 = new ForceXY(2,3);

        ForceXY resultant = VectorMath.calculateResultantForce(v1, v2, v3);

        assertEquals(3.0, resultant.xProjection());
        assertEquals(4.0, resultant.yProjection());
    }

    @Test
    public void testCalculateResultantForce_GivenNegativeComponents_ExpectCorrectResult() {
        ForceXY v1 = new ForceXY(0,-1);
        ForceXY v2 = new ForceXY(-1,0);

        ForceXY resultant = VectorMath.calculateResultantForce(v1, v2);

        assertEquals(-1.0, resultant.xProjection());
        assertEquals(-1.0, resultant.yProjection());
    }

    @Test
    public void testDirectionAngles_Given000to528_ExpectCorrectAngles() {
        VectorXYZ vector = new VectorXYZ(0, 5, 0, 2, 0, 8);

        assertEquals(Math.acos(5/Math.sqrt(93)), VectorMath.alpha(vector, AngleUnits.RADIANS));
        assertEquals(Math.acos(2/Math.sqrt(93)), VectorMath.beta(vector, AngleUnits.RADIANS));
        assertEquals(Math.acos(8/Math.sqrt(93)), VectorMath.gamma(vector, AngleUnits.RADIANS));

        assertEquals(58.77, VectorMath.alpha(vector, AngleUnits.DEGREES), 0.01);
        assertEquals(78.03, VectorMath.beta(vector, AngleUnits.DEGREES), 0.01);
        assertEquals(33.95, VectorMath.gamma(vector, AngleUnits.DEGREES), 0.01);
    }
}

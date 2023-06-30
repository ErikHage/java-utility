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

    @Test
    public void testDotProduct_Given123and456_Expect32() {
        Vector v1 = new VectorXYZ(1,2,3);
        Vector v2 = new VectorXYZ(4,5,6);

        double result = VectorMath.dotProduct(v1, v2);

        assertEquals(32, result);
    }

    @Test
    public void testCrossProduct_Given345and789_Expectneg4x8yneg4z() {
        Vector v1 = new VectorXYZ(3,4,5);
        Vector v2 = new VectorXYZ(7,8,9);

        Vector result = VectorMath.crossProduct(v1, v2);

        assertEquals(-4, result.xProjection());
        assertEquals(8, result.yProjection());
        assertEquals(-4, result.zProjection());
    }

    @Test
    void testAngle_GivenTwoVectors111andneg2neg2neg2_ExpectPi() {
        Vector v1 = new VectorXYZ(1,1,1);
        Vector v2 = new VectorXYZ(-2,-2,-2);

        assertEquals(Math.PI, VectorMath.angle(v1, v2));
    }
}

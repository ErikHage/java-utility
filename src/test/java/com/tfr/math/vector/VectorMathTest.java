package com.tfr.math.vector;

import com.tfr.math.trig.AngleUnits;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VectorMathTest {

    @Test
    public void testCalculateResultantForce_GivenTwoVectors_ExpectCorrectResult() {
        ForceXY v1 = new ForceXY(0,1);
        ForceXY v2 = new ForceXY(1,0);

        ForceXY resultant = VectorMath.calculateResultantForce(v1, v2);

        assertEquals(1.0, resultant.xProjection());
        assertEquals(1.0, resultant.yProjection());
        assertEquals(Math.sqrt(2.0), VectorMath.magnitude(resultant));
        assertEquals(45.0, resultant.direction(AngleUnits.DEGREES));
    }

    @Test
    public void testCalculateResultantForce_GivenThreeVectors_ExpectCorrectResult() {
        ForceXY v1 = new ForceXY(0,1);
        ForceXY v2 = new ForceXY(1,0);
        ForceXY v3 = new ForceXY(2,3);

        ForceXY resultant = VectorMath.calculateResultantForce(v1, v2, v3);

        assertEquals(3.0, resultant.xProjection());
        assertEquals(4.0, resultant.yProjection());
        assertEquals(5.0, VectorMath.magnitude(resultant));
        assertEquals(53.13, resultant.direction(AngleUnits.DEGREES), 0.001);
    }

    @Test
    public void testCalculateResultantForce_GivenNegativeComponents_ExpectCorrectResult() {
        ForceXY v1 = new ForceXY(0,-1);
        ForceXY v2 = new ForceXY(-1,0);

        ForceXY resultant = VectorMath.calculateResultantForce(v1, v2);

        assertEquals(-1.0, resultant.xProjection());
        assertEquals(-1.0, resultant.yProjection());
        assertEquals(Math.sqrt(2.0), VectorMath.magnitude(resultant));
        assertEquals(225.0, resultant.direction(AngleUnits.DEGREES));
    }

    @Test
    public void testAdjustAngle_GivenCaseO_ExpectException() {
        assertThrows(RuntimeException.class, () -> {
            VectorMath.adjustAngle(0.5, VectorDirection.O);
        });
    }

    @Test
    public void testAdjustAngle_GivenCaseE_Expect0() {
        double result = VectorMath.adjustAngle(0.5, VectorDirection.E);
        assertEquals(0, result);
    }

    @Test
    public void testAdjustAngle_GivenCaseNE_ExpectAngle() {
        double result = VectorMath.adjustAngle(0.5, VectorDirection.NE);
        assertEquals(0.5, result);
    }

    @Test
    public void testAdjustAngle_GivenCaseN_ExpectPiOver2() {
        double result = VectorMath.adjustAngle(0.5, VectorDirection.N);
        assertEquals(Math.PI / 2, result);
    }

    @Test
    public void testAdjustAngle_GivenCaseNW_ExpectPiMinusAngle() {
        double result = VectorMath.adjustAngle(0.5, VectorDirection.NW);
        assertEquals(Math.PI - 0.5, result);
    }

    @Test
    public void testAdjustAngle_GivenCaseW_ExpectPi() {
        double result = VectorMath.adjustAngle(0.5, VectorDirection.W);
        assertEquals(Math.PI, result);
    }

    @Test
    public void testAdjustAngle_GivenCaseSW_ExpectPiPlusAngle() {
        double result = VectorMath.adjustAngle(0.5, VectorDirection.SW);
        assertEquals(Math.PI + 0.5, result);
    }

    @Test
    public void testAdjustAngle_GivenCaseS_Expect3PiOver2() {
        double result = VectorMath.adjustAngle(0.5, VectorDirection.S);
        assertEquals((3.0 / 2.0) * Math.PI, result);
    }

    @Test
    public void testAdjustAngle_GivenCaseSE_Expect2PiMinusAngle() {
        double result = VectorMath.adjustAngle(0.5, VectorDirection.SE);
        assertEquals(2 * Math.PI - 0.5, result);
    }
}

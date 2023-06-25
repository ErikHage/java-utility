package com.tfr.math.trig;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrigMathTest {

    @Test
    public void testToRadians_Given180_ExpectPi() {
        assertEquals(Math.PI, TrigMath.toRadians(180));
    }

    @Test
    public void testToDegrees_GivenPi_Expect180() {
        assertEquals(180, TrigMath.toDegrees(Math.PI));
    }

    @Test
    public void testGetXProjection_Given100at30_Expect86ish() {
        assertEquals(86.60, TrigMath.getXProjection(100.0, Math.PI/6), 0.01);
    }

    @Test
    public void testGetYProjection_Given100at30_Expect49ish() {
        assertEquals(49.99, TrigMath.getYProjection(100.0, Math.PI/6), 0.01);
    }

    @Test
    public void testGetHypotenuse_Given3and4_Expect5() {
        assertEquals(5.0, TrigMath.getHypotenuse(3.0, 4.0));
    }
}

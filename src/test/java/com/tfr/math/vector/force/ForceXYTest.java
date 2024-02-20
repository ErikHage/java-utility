package com.tfr.math.vector.force;

import com.tfr.math.vector.VectorMath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForceXYTest {

    @Test
    public void testMagnitude_Givenx3y4_Expect5() {
        ForceXY vector = new ForceXY(3.0,4.0);

        Assertions.assertEquals(5.0, VectorMath.magnitude(vector));
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
}

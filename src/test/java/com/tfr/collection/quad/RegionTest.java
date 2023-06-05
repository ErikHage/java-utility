package com.tfr.collection.quad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegionTest {

    @Test
    public void testDoesOverlap_GivenOverlappingRegions_ExpectTrue() {
        Region region1 = new Region(0, 0, 100, 100);
        Region region2 = new Region(50, 50, 150,150);

        assertTrue(region1.doesOverlap(region2));
        assertTrue(region2.doesOverlap(region1));
    }

    @Test
    public void testDoesOverlap_GivenNonOverlappingRegions_ExpectFalse() {
        Region region1 = new Region(0, 0, 50, 10);
        Region region2 = new Region(50, 50, 150,150);

        assertFalse(region1.doesOverlap(region2));
        assertFalse(region2.doesOverlap(region1));
    }

    @Test
    public void testContainsPoint_GivenPointInRegion_ExpectTrue() {
        Region region = new Region(0, 0, 100, 100);
        Point point = new Point(5, 10);

        assertTrue(region.containsPoint(point));
    }

    @Test
    public void testContainsPoint_GivenPointNotInRegion_ExpectFalse() {
        Region region = new Region(0, 0, 100, 100);
        Point point = new Point(-5, -10);

        assertFalse(region.containsPoint(point));
    }

    @Test
    public void testGetQuadrant_GivenSW_ExpectLowerLeftQuadrant() {
        Region region = new Region(0, 0, 100, 100);

        Region quadrant = region.getQuadrant(Quadrant.SW);

        assertNotNull(quadrant);
        assertEquals(0, quadrant.x1());
        assertEquals(0, quadrant.y1());
        assertEquals(50, quadrant.x2());
        assertEquals(50, quadrant.y2());
    }

    @Test
    public void testGetQuadrant_GivenNW_ExpectUpperLeftQuadrant() {
        Region region = new Region(0, 0, 100, 100);

        Region quadrant = region.getQuadrant(Quadrant.NW);

        assertNotNull(quadrant);
        assertEquals(0, quadrant.x1());
        assertEquals(50, quadrant.y1());
        assertEquals(50, quadrant.x2());
        assertEquals(100, quadrant.y2());
    }

    @Test
    public void testGetQuadrant_GivenNE_ExpectUpperRightQuadrant() {
        Region region = new Region(0, 0, 100, 100);

        Region quadrant = region.getQuadrant(Quadrant.NE);

        assertNotNull(quadrant);
        assertEquals(50, quadrant.x1());
        assertEquals(50, quadrant.y1());
        assertEquals(100, quadrant.x2());
        assertEquals(100, quadrant.y2());
    }

    @Test
    public void testGetQuadrant_GivenSE_ExpectLowerRightQuadrant() {
        Region region = new Region(0, 0, 100, 100);

        Region quadrant = region.getQuadrant(Quadrant.SE);

        assertNotNull(quadrant);
        assertEquals(50, quadrant.x1());
        assertEquals(0, quadrant.y1());
        assertEquals(100, quadrant.x2());
        assertEquals(50, quadrant.y2());
    }
}

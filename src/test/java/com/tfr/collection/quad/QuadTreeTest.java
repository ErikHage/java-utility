package com.tfr.collection.quad;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuadTreeTest {

    private static QuadTree quadTree;

    @BeforeAll
    public static void setUp() {
        quadTree = getTestTree();
    }

    @Test
    public void testPrintTree_ReturnsString() {
        String tree = quadTree.printTree();
        System.out.println(tree);

        assertNotNull(tree);
    }

    @Test
    public void testInsert_GivenPointOutsideArea_ExpectFalse() {
        boolean result = quadTree.addPoint(new Point(1000, 1000));

        assertFalse(result);
    }

    @Test
    public void testSearch_GivenAreaOverlapsOnePoint_ExpectOneMatch() {
        Region targetRegion = new Region(200, 200, 250, 250);
        List<Point> matches = quadTree.search(targetRegion);

        assertEquals(1, matches.size());
        assertEquals(new Point(245, 238), matches.get(0));
    }

    @Test
    public void testSearch_GivenAreaOverlapsTwoPoints_ExpectTwoMatches() {
        Region targetRegion = new Region(0, 0, 100, 100);
        List<Point> matches = quadTree.search(targetRegion);

        List<Point> expectedPoints = List.of(
            new Point(21, 25),
            new Point(55, 53)
        );

        assertEquals(2, matches.size());
        assertArrayEquals(expectedPoints.toArray(), matches.toArray());
    }

    @Test
    public void testSearch_GivenAreaDoesNotOverlapsTree_ExpectNoMatches() {
        Region targetRegion = new Region(450, 500, 450, 500);
        List<Point> matches = quadTree.search(targetRegion);

        assertEquals(0, matches.size());
    }

    private static QuadTree getTestTree() {
        Region area = new Region(0, 0, 400, 400);
        quadTree = new QuadTree(area);

        List.of(
            new Point(21, 25),
            new Point(55, 53),
            new Point(70, 318),
            new Point(98, 302),
            new Point(49, 229),
            new Point(135, 229),
            new Point(224, 292),
            new Point(206, 321),
            new Point(197, 258),
            new Point(245, 238)
        ).forEach(quadTree::addPoint);

        return quadTree;
    }
}

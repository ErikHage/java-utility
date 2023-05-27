package com.tfr.collection.quad;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuadTree {
    private static final int MAX_POINTS = 3;

    private final Region area;
    private final List<Point> points = new ArrayList<>();
    private final Map<Quadrant, QuadTree> children = new HashMap<>();

    public QuadTree(Region area) {
        this.area = area;
    }

    public boolean addPoint(Point point) {
        if (this.area.containsPoint(point)) {
            if (this.points.size() < MAX_POINTS) {
                this.points.add(point);
                return true;
            } else {
                if (this.children.isEmpty()) {
                    createQuadrants();
                }
                return addPointToOneQuadrant(point);
            }
        }

        return false;
    }

    public List<Point> search(Region searchRegion) {
        return search(searchRegion, null);
    }

    private List<Point> search(Region searchRegion, List<Point> matches) {
        if (matches == null) {
            matches = new ArrayList<>();
        }

        if (!this.area.doesOverlap(searchRegion)) {
            return matches;
        } else {
            for (Point point: points) {
                if (searchRegion.containsPoint(point)) {
                    matches.add(point);
                }
            }
            if (this.children.size() > 0) {
                for (QuadTree child: children.values()) {
                    child.search(searchRegion, matches);
                }
            }
        }

        return matches;
    }

    private boolean addPointToOneQuadrant(Point point) {
        boolean isPointAdded;

        for (Quadrant quadrant: Quadrant.set()) {
            isPointAdded = this.children.get(quadrant).addPoint(point);
            if (isPointAdded) {
                return true;
            }
        }

        return false;
    }

    private void createQuadrants() {
        Region region;
        for (Quadrant quadrant: Quadrant.set()) {
            region = this.area.getQuadrant(quadrant);
            children.put(quadrant, new QuadTree(region));
        }
    }

    public String printTree() {
        return printTree("");
    }

    private String printTree(String depthIndicator) {
        StringBuilder str = new StringBuilder();
        if ("".equals(depthIndicator)) {
            str.append("Root-->")
                    .append(area.toString())
                    .append("\n");
        }

        for (Point point : points) {
            str.append(depthIndicator)
                    .append(point.toString())
                    .append("\n");
        }

        for (Map.Entry<Quadrant, QuadTree> childEntry: children.entrySet()) {
            str.append(depthIndicator)
                    .append("Q:")
                    .append(childEntry.getKey())
                    .append("-->")
                    .append(childEntry.getValue().area)
                    .append("\n");
            str.append(childEntry.getValue().printTree(depthIndicator + "\t"));
        }

        return str.toString();
    }
}

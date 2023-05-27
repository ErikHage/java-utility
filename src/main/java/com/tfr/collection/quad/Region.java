package com.tfr.collection.quad;

public record Region(
        float x1,
        float y1,
        float x2,
        float y2
) {

    public boolean containsPoint(Point point) {
        return point.x() >= this.x1
                && point.x() < this.x2
                && point.y() >= this.y1
                && point.y() < this.y2;
    }

    public boolean doesOverlap(Region otherRegion) {
        if (otherRegion.x2 < this.x1) {
            return false;
        }
        if (otherRegion.x1 > this.x2) {
            return false;
        }
        if (otherRegion.y1 > this.y2) {
            return false;
        }
        if (otherRegion.y2 < this.y1) {
            return false;
        }
        return true;
    }

    public Region getQuadrant(Quadrant quadrant) {
        float quadrantWidth = (this.x2 - this.x1) / 2;
        float quadrantHeight = (this.y2 - this.y1) / 2;

        switch (quadrant) {
            case SW -> {
                return new Region(x1, y1, x1 + quadrantWidth, y1 + quadrantHeight);
            }
            case NW -> {
                return new Region(x1, y1 + quadrantHeight, x1 + quadrantWidth, y2);
            }
            case NE -> {
                return new Region(x1 + quadrantWidth, y1 + quadrantHeight, x2, y2);
            }
            case SE -> {
                return new Region(x1 + quadrantHeight, y1, x2, y1 + quadrantHeight);
            }
        }
        return null;
    }
}

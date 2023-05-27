package com.tfr.collection.quad;

import java.util.Set;

public enum Quadrant {
    SW, NW, NE, SE;

    public static Set<Quadrant> set() {
        return Set.of(SW, NW, NE, SE);
    }
}

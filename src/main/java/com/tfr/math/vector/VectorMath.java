package com.tfr.math.vector;

public class VectorMath {

    public static VectorDirection getVectorDirection(Point p1, Point p2) {
        double x = p2.x() - p1.x();
        double y = p2.y() - p1.y();

        if (x == 0) {
            if (y == 0) {
                return VectorDirection.O;
            } else if (y > 0) {
                return VectorDirection.N;
            } else if (y < 0) {
                return VectorDirection.S;
            }
        } else if (x > 0) {
            if (y == 0) {
                return VectorDirection.E;
            } else if (y > 0) {
                return VectorDirection.NE;
            } else if (y < 0) {
                return VectorDirection.SE;
            }
        } else if (x < 0) {
            if (y == 0) {
                return VectorDirection.W;
            } else if (y > 0) {
                return VectorDirection.NW;
            } else if (y < 0) {
                return VectorDirection.SW;
            }
        }
        throw new RuntimeException("uh-oh");
    }

    public static double adjustAngle(double angle, VectorDirection vectorDirection) {
        switch (vectorDirection) {
            case O -> throw new RuntimeException("vector has magnitude 0, has no direction");
            case E -> { return 0; }
            case NE -> { return angle; }
            case N -> { return Math.PI / 2; }
            case NW -> { return Math.PI - angle; }
            case W -> { return Math.PI; }
            case SW -> { return Math.PI + angle; }
            case S -> { return (3.0 / 2.0) * Math.PI; }
            case SE -> { return (2 * Math.PI) - angle; }
            default -> throw new RuntimeException("how did you get here?");
        }
    }

    public static ForceXY calculateResultantForce(ForceXY... vectors) {
        double xComponents = 0.0;
        double yComponents = 0.0;

        for (ForceXY v: vectors) {
            xComponents += v.x();
            yComponents += v.y();
        }

        return new ForceXY(xComponents, yComponents);
    }
}

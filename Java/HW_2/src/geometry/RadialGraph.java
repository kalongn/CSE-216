package geometry;

import java.util.*;

public class RadialGraph extends Shape {

    public Point center;
    public List<Point> neighbors;

    public RadialGraph(Point center, List<Point> neighbors) {
        this.center = center;
        // Need to check for the nope scenario.
        if (!isAllEdgesSameLength(neighbors)) {
            throw new IllegalArgumentException("Edges are not the same length");
        }
        this.neighbors = neighbors;
    }

    public RadialGraph(Point center) {
        this.center = center;
        this.neighbors = null;
    }

    @Override
    public RadialGraph rotateBy(int degrees) {
        if (neighbors == null) {
            return this;
        }
        List<Point> newNeightbors = new ArrayList<>();
        for (Point i : this.neighbors) {
            newNeightbors.add(rotatePoint(i, degrees));
        }
        return new RadialGraph(center, (List<Point>) newNeightbors);
    }

    @Override
    public RadialGraph translateBy(double x, double y) {
        center = translatePoint(center, x, y);
        if (neighbors == null) {
            return new RadialGraph(center);
        }
        List<Point> newNeightbors = new ArrayList<>();
        for (Point i : this.neighbors) {
            newNeightbors.add(translatePoint(i, x, y));
        }
        return new RadialGraph(center, (List<Point>) newNeightbors);
    }

    @Override
    public String toString() {
        if (neighbors == null) {
            return new StringJoiner(",", "[", "]").add(center.toString()).toString();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < neighbors.size(); i++) {
            sb.append(neighbors.get(i).toString());
            if (i != neighbors.size() - 1) {
                sb.append(",");
            }
        }
        return new StringJoiner(",", "[", "]").add(center.toString()).add(sb.toString()).toString();
    }

    @Override
    public Point center() {
        return center;
    }

    // My Own implement method

    private static Point rotatePoint(Point p, int degrees) {
        double newX = round(p.x * Math.cos(Math.toRadians(degrees)) - p.y * Math.sin(Math.toRadians(degrees)));
        double newY = round(p.x * Math.sin(Math.toRadians(degrees)) + p.y * Math.cos(Math.toRadians(degrees)));
        return new Point(p.name, newX, newY);
    }

    private static Point translatePoint(Point p, double x, double y) {
        return new Point(p.name, round(p.x + x), round(p.y + y));
    }

    private boolean isAllEdgesSameLength(List<Point> neighbors) {
        if (neighbors == null || neighbors.size() <= 1) {
            return true;
        }
        for (int i = 1; i < neighbors.size(); i++) {
            if (edgesLengthFromCenter(neighbors.get(i)) != edgesLengthFromCenter(neighbors.get(i - 1))) {
                return false;
            }
        }
        return true;
    }

    private double edgesLengthFromCenter(Point node) {
        if (node.x == center.x && node.y == center.y) {
            return 0.0;
        }
        return Math.sqrt(Math.pow((node.x - center.x), 2) + Math.pow(node.y - center.y, 2));
    }

    private static double round(double value) {
        value *= 100;
        value = Math.round(value);
        value /= 100;
        return value;
    }

    /*
     * Driver method given to you as an outline for testing your code. You can
     * modify this as you want, but please keep
     * in mind that the lines already provided here as expected to work exactly as
     * they are (some lines have additional
     * explanation of what is expected)
     */
    public static void main(String... args) {
        Point center = new Point("center", 0, 0);
        Point east = new Point("east", 1, 0);
        Point west = new Point("west", -1, 0);
        Point north = new Point("north", 0, 1);
        Point south = new Point("south", 0, -1);
        Point toofarsouth = new Point("south", 0, -2);

        // A single node is a valid radial graph.
        RadialGraph lonely = new RadialGraph(center);

        // Must print: [(center, 0.0, 0.0)]
        System.out.println(lonely);

        // This line must throw IllegalArgumentException, since the edges will not be of
        // the same length

        try {
            RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth, east, west));
            System.out.println(nope);
        } catch (IllegalArgumentException ex) {
            System.out.println("Exception test passed.");
        }

        Shape g = new RadialGraph(center, Arrays.asList(north, south, east, west));

        // Must follow the documentation in the Shape abstract class, and print the
        // following string:
        // [(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0);
        // (south, 0.0, -1.0)]
        System.out.println(g);

        // After this counterclockwise rotation by 90 degrees, "north" must be at (-1,
        // 0), and similarly for all the
        // other radial points. The center, however, must remain exactly where it was.
        g = g.rotateBy(90);
        System.out.println(g);
        // you should similarly add tests for the translateBy(x, y) method
        g = g.translateBy(1, 1);
        System.out.println(g);
    }
}

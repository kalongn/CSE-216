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
        double tolerance = 1e-16, newX, newY;
        for (Point i : this.neighbors) {
            newX = i.x * Math.cos(Math.toRadians(degrees)) - i.y * Math.sin(Math.toRadians(degrees));
            newY = i.x * Math.sin(Math.toRadians(degrees)) + i.y * Math.cos(Math.toRadians(degrees));
            if (tolerance > Math.abs(newX)) {
                newX = 0.0;
            }
            if (tolerance > Math.abs(newY)) {
                newY = 0.0;
            }
            newNeightbors.add(new Point(i.name, newX, newY));

        }
        return new RadialGraph(center, (List<Point>) newNeightbors);
    }

    @Override
    public RadialGraph translateBy(double x, double y) {
        center = new Point(center.name, center.x + x, center.y + y);
        if (neighbors == null) {
            return new RadialGraph(center);
        }
        List<Point> newNeightbors = new ArrayList<>();
        for (Point i : this.neighbors) {
            newNeightbors.add(new Point(i.name, i.x + x, i.y + y));
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

    public boolean isAllEdgesSameLength(List<Point> neighbors) {
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

    public double edgesLengthFromCenter(Point node) {
        if (node.x == center.x && node.y == center.y) {
            return 0.0;
        }
        return Math.sqrt(Math.pow((node.x - center.x), 2) + Math.pow(node.y - center.y, 2));
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
        // Point toofarsouth = new Point("south", 0, -2);

        // A single node is a valid radial graph.
        RadialGraph lonely = new RadialGraph(center);

        // Must print: [(center, 0.0, 0.0)]
        System.out.println(lonely);

        // This line must throw IllegalArgumentException, since the edges will not be of
        // the same length
        // RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth, east, west));

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

package geometry;

import java.util.*;

public class RadialGraph extends Shape {

    public final Point center;
    public final List<Point> neighbors;

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
        List<Point> newNeightbors = translateAllPointsToCenter(center, neighbors);
        for (int i = 0; i < newNeightbors.size(); i++) {
            newNeightbors.set(i, rotatePoint(newNeightbors.get(i), degrees));
        }
        newNeightbors = translateAllPointsBack(center, newNeightbors);
        return new RadialGraph(center, (List<Point>) newNeightbors);
    }

    @Override
    public RadialGraph translateBy(double x, double y) {
        Point newCenter = translatePoint(center, x, y);
        if (neighbors == null) {
            return new RadialGraph(center);
        }
        List<Point> newNeightbors = new ArrayList<>();
        for (Point i : this.neighbors) {
            newNeightbors.add(translatePoint(i, x, y));
        }
        return new RadialGraph(newCenter, (List<Point>) newNeightbors);
    }

    @Override
    public String toString() {
        Point roundedCenter = new Point("center", round(center.x), round(center.y));
        if (neighbors == null) {
            return new StringJoiner(",", "[", "]").add(roundedCenter.toString()).toString();
        }
        List<Point> newNeightbors = translateAllPointsToCenter(center, neighbors);
        double radians[] = allPointsToDegree(newNeightbors);
        List<Point> sortedNewNeightbors = translateAllPointsBack(center, insertionSort(radians, newNeightbors));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sortedNewNeightbors.size(); i++) {
            Point rounded = new Point(sortedNewNeightbors.get(i).name, round(sortedNewNeightbors.get(i).x),
                    round(sortedNewNeightbors.get(i).y));
            sb.append(rounded.toString());
            if (i != sortedNewNeightbors.size() - 1) {
                sb.append(",");
            }
        }
        return new StringJoiner(",", "[", "]").add(roundedCenter.toString()).add(sb.toString()).toString();
    }

    @Override
    public Point center() {
        return center;
    }

    // My Own implement methods

    private static Point rotatePoint(Point p, int degrees) {
        double newX = p.x * Math.cos(Math.toRadians(degrees)) - p.y * Math.sin(Math.toRadians(degrees));
        double newY = p.x * Math.sin(Math.toRadians(degrees)) + p.y * Math.cos(Math.toRadians(degrees));
        return new Point(p.name, newX, newY);
    }

    private static Point translatePoint(Point p, double x, double y) {
        return new Point(p.name, p.x + x, p.y + y);
    }

    private boolean isAllEdgesSameLength(List<Point> neighbors) {
        if (neighbors == null || neighbors.size() <= 1) {
            return true;
        }
        double tolerance = 1e-10;
        for (int i = 1; i < neighbors.size(); i++) {
            if (Math.abs(edgesLengthFromCenter(neighbors.get(i))
                    - edgesLengthFromCenter(neighbors.get(i - 1))) > tolerance) {
                return false;
            }
        }
        return true;
    }

    private static List<Point> translateAllPointsToCenter(Point center, List<Point> neighbors) {
        for (int i = 0; i < neighbors.size(); i++) {
            neighbors.set(i, translatePoint(neighbors.get(i), -1 * center.x, -1 * center.y));
        }
        return neighbors;
    }

    private static List<Point> translateAllPointsBack(Point center, List<Point> neighbors) {
        for (int i = 0; i < neighbors.size(); i++) {
            neighbors.set(i, translatePoint(neighbors.get(i), center.x, center.y));
        }
        return neighbors;
    }

    private static double[] allPointsToDegree(List<Point> neighbors) {
        double radians[] = new double[neighbors.size()];
        for (int i = 0; i < neighbors.size(); i++) {
            radians[i] = (360 + Math.toDegrees(Math.atan2(neighbors.get(i).y, neighbors.get(i).x))) % 360;
        }
        return radians;
    }

    private static List<Point> insertionSort(double[] thetas, List<Point> neighbors) {
        int n = thetas.length;
        for (int i = 1; i < n; i++) {
            double thetaKey = thetas[i];
            Point pointKey = neighbors.get(i);
            int j = i - 1;

            while (j >= 0 && thetas[j] > thetaKey) {
                thetas[j + 1] = thetas[j];
                neighbors.set(j + 1, neighbors.get(j));
                j = j - 1;
            }
            thetas[j + 1] = thetaKey;
            neighbors.set(j + 1, pointKey);
        }
        return neighbors;
    }

    // modified this with Tolerance.
    private double edgesLengthFromCenter(Point node) {
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

package geometry;

public class Square extends Shape {

    public final Point a, b, c, d;

    @Override
    public Point center() {
        return new Point("center", round((a.x + c.x) / 2.0), round((a.y + c.y) / 2.0));
    }

    @Override
    public Square rotateBy(int degrees) {
        Point[] points = new Point[] { a, b, c, d };
        for (int i = 0; i < points.length; i++) {
            points[i] = rotatePoint(points[i], degrees);
        }
        return new Square(points[0], points[1], points[2], points[3]);
    }

    @Override
    public Shape translateBy(double x, double y) {
        Point[] points = new Point[] { a, b, c, d };
        for (int i = 0; i < points.length; i++) {
            points[i] = translatePoint(points[i], x, y);
        }
        return new Square(points[0], points[1], points[2], points[3]);
    }

    @Override
    public String toString() {
        Point[] points = new Point[] { a, b, c, d };
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < points.length; i++) {
            Point rounded = new Point(points[i].name, round(points[i].x), round(points[i].y));
            sb.append(rounded.toString());
            if (i != points.length - 1) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public Square(Point a, Point b, Point c, Point d) {
        if (!isValidSquare(a, b, c, d)) {
            throw new IllegalArgumentException("Input points cannot form a valid square.");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    // My Own implement methods

    // modified this with Tolerance.
    private static double edgesLength(Point p1, Point p2) {
        if (p1.x == p2.x && p1.y == p2.y) {
            return 0.0;
        }
        return Math.sqrt(Math.pow((p1.x - p2.x), 2) + Math.pow(p1.y - p2.y, 2));
    }

    private boolean isValidSquare(Point a, Point b, Point c, Point d) {
        if (!isValidSquareHelper(a, b, c) || !isValidSquareHelper(b, c, d) || !isValidSquareHelper(c, d, a)) {
            return false;
        }
        return true;
    }

    private static boolean isValidSquareHelper(Point one, Point two, Point three) {
        double tolerance = 0.01d;
        if (Math.abs(edgesLength(one, two) - edgesLength(two, three)) > tolerance) {
            return false;
        }
        return true;
    }

    private static Point rotatePoint(Point p, int degrees) {
        double newX = p.x * Math.cos(Math.toRadians(degrees)) - p.y * Math.sin(Math.toRadians(degrees));
        double newY = p.x * Math.sin(Math.toRadians(degrees)) + p.y * Math.cos(Math.toRadians(degrees));
        double lowerBoundTolerance = 1e-10, upperBoundTolerance = 0.9999999999;
        if (lowerBoundTolerance > Math.abs(newX)) {
            newX = 0.0;
        }
        if (upperBoundTolerance < Math.abs(newX)) {
            newX = round(newX);
        }
        if (lowerBoundTolerance > Math.abs(newY)) {
            newY = 0.0;
        }
        if (upperBoundTolerance < Math.abs(newY)) {
            newY = round(newY);
        }
        return new Point(p.name, newX, newY);
    }

    private static Point translatePoint(Point p, double x, double y) {
        return new Point(p.name, p.x + x, p.y + y);
    }

    private static double round(double value) {
        value *= 100;
        value = Math.round(value);
        value /= 100;
        return value;
    }

    public static void main(String... args) {
        // Normal square:
        Shape sq1 = new Square(new Point("upper-right", 1, 1), new Point("upper-left", 0, 1),
                new Point("lower-left", 0, 0), new Point("lower-right", 1, 0));
        // Fail case square:
        try {
            Shape sq2 = new Square(new Point("lower-right", 1, 0), new Point("upper-left", 0, 1),
                    new Point("lower-left", 0, 0), new Point("upper-right", 1, 1));
            System.out.println(sq2);
        } catch (IllegalArgumentException ex) {
            System.out.println("Exception test pass");
        }
        // printing square:
        System.out.println(sq1);
        // Rotate Square:
        sq1 = ((Square) sq1).rotateBy(90);
        System.out.println(sq1);
        sq1 = ((Square) sq1).rotateBy(390);
        System.out.println(sq1);
        sq1 = ((Square) sq1).rotateBy(330);
        System.out.println(sq1);
        sq1 = ((Square) sq1).translateBy(100, -100);
        System.out.println(sq1);

        // center of Square:
        System.out.println(sq1.center());
    }
}

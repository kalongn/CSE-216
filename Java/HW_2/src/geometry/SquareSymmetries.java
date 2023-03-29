package geometry;

import java.util.List;
import java.util.ArrayList;

public class SquareSymmetries implements Symmetries<Square> {

    @Override
    public boolean areSymmetric(Square s1, Square s2) {
        List<Square> allSymmetries = symmetriesOf(s1);
        for (Square i : allSymmetries) {
            if (isSquareAboutTheSame(i, s2)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Square> symmetriesOf(Square s) {
        List<Square> result = new ArrayList<>();
        result.addAll(rotationSymmetriesOf(s));
        result.addAll(reflectionSymmetriesOf(s));
        return (List<Square>) result;
    }

    // My Own implement methods

    private static boolean isSquareAboutTheSame(Square one, Square two) {
        double tolerance = 1e-10;
        if (Math.abs(one.a.x - two.a.x) > tolerance || Math.abs(one.a.y - two.a.y) > tolerance) {
            return false;
        }
        if (Math.abs(one.b.x - two.b.x) > tolerance || Math.abs(one.b.y - two.b.y) > tolerance) {
            return false;
        }
        if (Math.abs(one.c.x - two.c.x) > tolerance || Math.abs(one.c.y - two.c.y) > tolerance) {
            return false;
        }
        if (Math.abs(one.d.x - two.d.x) > tolerance || Math.abs(one.d.y - two.d.y) > tolerance) {
            return false;
        }
        return true;
    }

    private static List<Square> rotationSymmetriesOf(Square s) {
        final int FULL_ANGLE = 360;
        final int RIGHT_ANGLE = 90;
        List<Square> result = new ArrayList<>();
        for (int i = 0; i < FULL_ANGLE; i += RIGHT_ANGLE) {
            result.add(s.rotateBy(i));
        }
        return (List<Square>) result;
    }

    private static List<Square> reflectionSymmetriesOf(Square s) {
        List<Square> result = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            result.add(singleReflectOf(s, i));
        }
        return (List<Square>) result;
    }

    private static Square singleReflectOf(Square s, int waysOfReflection) {
        Point newA = new Point(s.a.name, s.a.x, s.a.y);
        Point newB = new Point(s.b.name, s.b.x, s.b.y);
        Point newC = new Point(s.c.name, s.c.x, s.c.y);
        Point newD = new Point(s.d.name, s.d.x, s.d.y);
        switch (waysOfReflection) {
            case 0:
                return new Square(newB, newA, newD, newC);
            case 1:
                return new Square(newC, newD, newA, newB);
            case 2:
                return new Square(newC, newB, newA, newD);
            case 3:
                return new Square(newA, newD, newC, newB);
            default:
                throw new IllegalArgumentException("waysOfReflection is not within valid range");
        }
    }
}

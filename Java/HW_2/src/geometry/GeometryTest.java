package geometry;

import java.util.Arrays;
import java.util.List;

/**
 * This class is given to you as an outline for testing your code. You can modify this as you want, but please keep in
 * mind that the lines already provided here as expected to work exactly as they are.
 *
 * @author Ritwik Banerjee
 */
public class GeometryTest {

    public static void main(String... args) {
        testRadialGraphSymmetries();
        System.out.println("---------------");
        testSquareSymmetries();
    }

    private static void testRadialGraphSymmetries() {

        //4 side
        Point center = new Point("center", 0, 0);
        Point east   = new Point("east", 1, 0);
        Point west   = new Point("west", -1, 0);
        Point north  = new Point("north", 0, 1);
        Point south  = new Point("south", 0, -1);

        RadialGraph g1 = new RadialGraph(center, Arrays.asList(north, south, east, west));
        RadialGraph g2 = g1.rotateBy(45);
        RadialGraph g3 = g1.rotateBy(360);
        RadialGraph g4 = g1.rotateBy(180);

        RadialGraphSymmetries graphSymmetries = new RadialGraphSymmetries();
        System.out.println(graphSymmetries.areSymmetric(g1, g2)); // must return false
        System.out.println(graphSymmetries.areSymmetric(g1, g3)); // must return true
        System.out.println(graphSymmetries.areSymmetric(g3, g4)); // must return true

        // obtain all the symmetries (including the identity) of g1, and print them one by one (remember that printing
        // will give the string representation of each radial graph, which must follow the specification of Shape's
        // toString() method)
        List<RadialGraph> symmetries = graphSymmetries.symmetriesOf(g1);
        for (RadialGraph g : symmetries) System.out.println(g);

        //3 side
        Point centerTrig = new Point("center", 0,  Math.sqrt(3)/3);
        Point topTrig = new Point("top", 0, Math.sqrt(3));
        Point leftTrig = new Point("left", -1, 0);
        Point rightTrig = new Point("right", 1, 0);
        RadialGraph g5 = new RadialGraph(centerTrig, Arrays.asList(topTrig, leftTrig, rightTrig));
        System.out.println(g5);
        RadialGraph g6 = g5.rotateBy(120);
        System.out.println(g6);
        System.out.println(graphSymmetries.areSymmetric(g5, g6));

        List<RadialGraph> symmetriesOfTrig = graphSymmetries.symmetriesOf(g5);
        for (RadialGraph g : symmetriesOfTrig) System.out.println(g);


        //5 side
        double c1= Math.cos(2*Math.PI / 5);
        double c2= Math.cos(Math.PI / 5);
        double s1 = Math.sin(2*Math.PI / 5);
        double s2 = Math.sin(4*Math.PI / 5);

        Point centerPentagon = new Point("center", 0, 0);
        Point first = new Point("one", 0,1);
        Point second = new Point("two", s1, c1);
        Point third = new Point("three", s2, -1.0*c2);
        Point fouth = new Point("fouth", -1.0*s2, -1.0*c2);
        Point fifth = new Point("fifth", -1.0*s1, c1);

        RadialGraph g7 = new RadialGraph(centerPentagon, Arrays.asList(first,second,third,fouth,fifth));
        System.out.println(g7);
        RadialGraph g8 = g7.rotateBy(72);
        RadialGraph g9 = g7.rotateBy(80);
        System.out.println(g8);
        System.out.println(g9);
        System.out.println(graphSymmetries.areSymmetric(g8, g9));
        System.out.println(graphSymmetries.areSymmetric(g8, g7));
    }

    private static void testSquareSymmetries() {
        Square sq1 = new Square(new Point("2", 1, 1), new Point("1", 0, 1),
                                new Point("4", 0, 0), new Point("3", 1, 0));
        Square sq2 = sq1.rotateBy(30);
        Square sq3 = sq1.rotateBy(180);

        SquareSymmetries squareSymmetries = new SquareSymmetries();
        System.out.println(squareSymmetries.areSymmetric(sq1, sq2)); // must return false
        System.out.println(squareSymmetries.areSymmetric(sq1, sq3)); // must return true

        // obtain all the 8 symmetries (including the identity) of sq1, and print them one by one (remember that printing
        // will give the string representation of each square, which must follow the specification of Shape's toString()
        // method)
        List<Square> symmetries = squareSymmetries.symmetriesOf(sq1);
        for (Square s : symmetries) System.out.println(s);
    }
}
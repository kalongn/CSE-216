package geometry;

import java.util.List;
import java.util.ArrayList;

public class RadialGraphSymmetries implements Symmetries<RadialGraph> {

    @Override
    public boolean areSymmetric(RadialGraph s1, RadialGraph s2) {
        if (s1.neighbors.size() != s2.neighbors.size()) {
            return false;
        }
        if (s1.center.x != s1.center.x && s1.center.y != s2.center.y) {
            return false;
        }
        List<RadialGraph> allVariants = symmetriesOf(s1);
        for (RadialGraph i : allVariants) {
            int z = 0;
            innerloop: for (; z < s1.neighbors.size(); z++) {
                if (i.neighbors.get(z).x != s2.neighbors.get(z).x || i.neighbors.get(z).y != s2.neighbors.get(z).y) {
                    break innerloop;
                }
            }
            if (z == s1.neighbors.size()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<RadialGraph> symmetriesOf(RadialGraph s) {
        final int FULL_ANGLE = 360;
        if (360 % s.neighbors.size() != 0) {
            throw new IllegalArgumentException(
                    "Expect 360 can be divided by the graph neightbors but the graph input does not. ");
        }
        final int ROTATE_ANGLE = FULL_ANGLE / s.neighbors.size();
        List<RadialGraph> result = new ArrayList<>();
        for (int i = 0; i < 360; i += ROTATE_ANGLE) {
            result.add(s.rotateBy(i));
        }
        return (List<RadialGraph>) result;
    }

}

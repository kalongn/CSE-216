package arithmetic;

import core.Group;

public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne> {

    @Override
    public PlusOrMinusOne binaryOperation(PlusOrMinusOne one, PlusOrMinusOne other) {
        return PlusOrMinusOne.multi(one, other);
    }

    @Override
    public PlusOrMinusOne identity() {
        return PlusOrMinusOne.PLUS_ONE;
    }

    @Override
    public PlusOrMinusOne inverseOf(PlusOrMinusOne t) {
        switch (t.value) {
            case 1:
                return PlusOrMinusOne.PLUS_ONE;
            default:
                return PlusOrMinusOne.MINUS_ONE;
        }
    }

}

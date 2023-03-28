package arithmetic;

public enum PlusOrMinusOne {
    PLUS_ONE(1),
    MINUS_ONE(-1);

    public final int value;

    private PlusOrMinusOne(int value) {
        if (value != 1 && value != -1) {
            throw new IllegalArgumentException("Can only be 1 or -1.");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + this.value;
    }
}
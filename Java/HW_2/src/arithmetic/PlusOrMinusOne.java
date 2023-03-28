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
/*
 * public class PlusOrMinusOne {
 * 
 * private int digit;
 * 
 * public PlusOrMinusOne(int digit) throws IllegalArgumentException {
 * if (digit != -1 && digit != 1) {
 * throw new IllegalArgumentException("The input value can only be 1 or -1.");
 * }
 * this.digit = digit;
 * }
 * 
 * public int getDigit() {
 * return this.digit;
 * }
 * 
 * public static PlusOrMinusOne multi(PlusOrMinusOne one, PlusOrMinusOne two) {
 * return new PlusOrMinusOne(one.digit * two.digit);
 * }
 * 
 * public static PlusOrMinusOne[] values() {
 * PlusOrMinusOne[] result = new PlusOrMinusOne[2];
 * result[0] = new PlusOrMinusOne(-1);
 * result[1] = new PlusOrMinusOne(1);
 * return result;
 * }
 * 
 * @Override
 * public String toString() {
 * return "" + digit;
 * }
 * 
 * }
 */

import java.util.function.BiFunction;

public class HigherOrderUtils {
    public static interface NamedBiFunction<T, U, R> extends BiFunction<T, U, R> {
        String name();
    }

    public static NamedBiFunction<Double, Double, Double> add = new NamedBiFunction<>() {

        @Override
        public Double apply(Double t, Double u) {
            return t + u;
        }

        @Override
        public String name() {
            return "plus";
        }

    };

    public static NamedBiFunction<Double, Double, Double> subtract = new NamedBiFunction<>() {

        @Override
        public Double apply(Double t, Double u) {
            return t - u;
        }

        @Override
        public String name() {
            return "minus";
        }

    };

    public static NamedBiFunction<Double, Double, Double> multiply = new NamedBiFunction<>() {

        @Override
        public Double apply(Double t, Double u) {
            return t * u;
        }

        @Override
        public String name() {
            return "mult";
        }

    };

    public static NamedBiFunction<Double, Double, Double> div = new NamedBiFunction<>() {

        @Override
        public Double apply(Double t, Double u) {
            if (u == 0) {
                throw new java.lang.ArithmeticException("Cannot divide by 0.");
            }
            return t / u;
        }

        @Override
        public String name() {
            return "div";
        }

    };
}

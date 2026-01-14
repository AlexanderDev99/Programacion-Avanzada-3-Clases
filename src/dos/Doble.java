package dos;

public class Doble implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer x) {
        return x * 2;
    }

    @Override
    public Function compose(Function t) {
        return Function.super.compose(t);
    }
}

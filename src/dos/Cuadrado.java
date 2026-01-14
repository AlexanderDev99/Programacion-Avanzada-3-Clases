package dos;

public class Cuadrado implements Function<Integer, Integer> {


    @Override
    public Integer apply(Integer x) {
        return x * x;
    }

    @Override
    public Function compose(Function t) {
        return Function.super.compose(t);
    }
}

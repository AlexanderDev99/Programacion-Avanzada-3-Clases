package dos;

@FunctionalInterface
public interface Function<T, U> {
    U apply(T x);

    default <V> Function<V, U>
    compose(Function<V, T> t) {
        return (V x) -> this.apply(t.apply(x));
    }

    public static <T, U, V> Function<Function<U, V>,
            Function<Function<T, U>,
                    Function<T, V>>> higerCompose() {
        return f -> g -> x -> f.apply(g.apply(x));
    }
}

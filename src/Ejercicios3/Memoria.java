package Ejercicios3;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Memoria {

    static Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

    public static Integer doubleValue(Integer x) {
        // forma larga
        /*
         * if (cache.containsKey(x)) {
         * 
         * return cache.get(x);
         * } else {
         * System.out.println("Calculando " + x);
         * Integer ret = x * 2;
         * cache.put(x, ret);
         * return ret;
         * }
         */

        // forma corta
        return cache.computeIfAbsent(x, a -> a * 2);
    }

    public static Function<Integer, Integer> memorizar(Function<Integer, Integer> fn) {

        Memoria mem = new Memoria();
        return mem.doMemorizar(fn);

    }

    public Function<Integer, Integer> doMemorizar(Function<Integer, Integer> fn) {
        /*
         * return x -> {
         * if (cache.containsKey(x)) {
         * return cache.get(x);
         * } else {
         * Integer y = fn.apply(x);
         * cache.put(x, y);
         * return y;
         * }
         */
        return x -> cache.computeIfAbsent(x, a -> fn.apply(a));
    }

    public static void main(String[] args) {

        // doubleValue pero usando una funcion -> mas simple que declarando un metodo.
        Function<Integer, Integer> fn = x -> cache.computeIfAbsent(x, a -> a * 2);

        Integer a = doubleValue(2);
        Integer a2 = doubleValue(3);
        Integer a3 = doubleValue(2);
        Integer a4 = doubleValue(2);
        Integer a5 = doubleValue(2);
        Integer a6 = doubleValue(2);

        Function<Integer, Integer> fn2 = x -> x + x;
        Function<Integer, Integer> fn2Mem = memorizar(fn2);
        Function<Integer, Integer> fn3Mem = memorizar(x -> 3 * x);

    }
}

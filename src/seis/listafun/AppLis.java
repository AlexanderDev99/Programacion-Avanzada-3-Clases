package seis.listafun;

import dos.Function;

import java.security.PublicKey;
import java.util.*;

public class AppLis {
    public static <T> List<T> add(List<T> lis, T e) {
        List<T> tmp = copy(lis);
        tmp.add(e);
        return Collections.unmodifiableList(tmp);
    }

    public static <T> List<T> remove(List<T> list, T e) {
        List<T> tmp = copy(list);
        tmp.remove(e);
        return Collections.unmodifiableList(tmp);
    }

    public static <T> Optional<T> head(List<T> lis) {
        if (lis.size() == 0) {
            return Optional.empty();
        }

        return Optional.of(lis.get(0));
    }

    public static <T> Optional<List<T>> tail(List<T> lis) {
        if (lis.size() == 0) {
            return Optional.empty();
        }
        List<T> tmp = copy(lis);
        tmp.remove(0);

        return Optional.of(tmp);
    }

    public static <T> List<T> copy(List<T> lis) {
        return new ArrayList<T>(lis);
    }

    // Forma estandar
    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    // Forma funcional
    public static int factorial2(int n) {

        return n == 0 ? 1 : n * factorial2(n - 1); // operador ternario
    }

    public static Function<Integer, Integer> fact = null;

    //Bloques de inicializacion statica
    // inicializamos antes porque las variables locales no se inicializan.
    static {
        fact = n -> n == 0 ? 1 : n * fact.apply(n - 1);
    }

    // Ejemplo 1
    public static Function<Integer, Integer> suma = null;

    static {
        suma = n -> n == 0 ? 1 : n * suma.apply(n - 1);
    }

    //Ejemplo 2
    public static Function<Integer, Integer> cubo = null;

    static {

        cubo = n -> n % 2 == 1 ? 1 : n * cubo.apply(n);
    }


    public static void main(String[] args) {

        List<Integer> l1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> l2 = add(l1, 8);
        System.out.println(l1.toString());
        System.out.println(l2);
        List<Integer> l3 = remove(l2, 4);
        System.out.println(l3);

        int a = factorial2(3);
        System.out.println(a);

        System.out.println(fact.apply(7));
        System.out.println(suma.apply(3));
        System.out.println(cubo.apply(3));

        Lista<Integer> l4 = Lista.of(1, 2, 3, 4, 5);
        System.out.println(l4);

        //Usanod el Metodo Maping
        Lista<Integer> l5 = Lista.of(1, 2, 3, 4, 5, 6);
        Function<Integer, Double> precio = p -> p * 0.15;

        //Usando el drop
        Lista<Integer> l6 = Lista.of(1, 2, 3, 4, 5, 6);

        Function<Integer, String> fn = x -> " " + x;
        //Lista<String> resultado = Lista.of(1, 2, 3, 4, 5).map(fn);

        //resultado.forEach(s -> System.out.print(s));

       /* Function<Integer, Function<Integer, Integer>> f = x -> y -> x + y;

        Function<Double, Function<Lista<Integer>, Double>> fy = a -> list -> {

            int aux = 0;
            double x = 0;
            double c = x;
            while (!list.isEmty()) {
                c += list.head();
                aux++;
                list.tail();
            }

            return c / aux;
        };*/

    }
}

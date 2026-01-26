package Ejercicios3;

import java.math.BigInteger;

//CORECURSIVIDAD
public class MainApp {

    // Metodo fibonaci recursivo
    public static Integer fib(Integer n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return fib(n - 1) + fib(n - 2);
        }
    }

    // Metodo fibonaci correcursivo

    public static Integer fib2(Integer n) {

        // x1 ,x2 es memoria implicita que guarda los calculos anteriores
        Integer x1 = 1;
        Integer x2 = 1;
        Integer f = 0;

        for (int i = 3; i <= n; i++) {
            f = x1 + x2;
            x1 = x2;
            x2 = f;
        }

        return f;
    }

    // Nota si usamos Integer a partir de un n = 50 elprograma se cae, por eso es
    // mejor usar BigInteger
    public static BigInteger fib3(BigInteger n) {

        // x1 ,x2 es memoria implicita que guarda los calculos anteriores
        BigInteger x1 = BigInteger.ONE;
        BigInteger x2 = BigInteger.ONE;
        BigInteger f = BigInteger.ZERO;

        for (int i = 3; i <= n.longValue(); i++) {
            f = x1.add(x2);
            x1 = x2;
            x2 = f;
        }

        return f;
    }

    public static void main(String[] args) {

        Integer n = 40;
        Integer n2 = 50;
        // Integer res = fib(n);
        // System.out.println(res);

        // fibonaci recursivo
        System.out.println("-------> Fibonaci recursivo");
        for (int i = 0; i <= n; i++) {
            Integer r = fib(i);
            System.out.println("fib: " + r);
        }

        // fibonaci correcursivo: mas eficiente porque guardaen meoria los valores
        // anteriores.
        System.out.println("\n-------> Fibonaci corecursivo");
        for (int i = 0; i <= n; i++) {
            Integer r = fib2(i);
            System.out.println("fib: " + r);
        }

        //Fibonaci con BigInteger
        System.out.println("\n-------> Fibonaci corecursivo BigInteger (n > 50)");
        for (int i = 0; i <= n2; i++) {
            BigInteger r = fib3(BigInteger.valueOf(i));
            System.out.println("fib: " + r);
        }

    }
}

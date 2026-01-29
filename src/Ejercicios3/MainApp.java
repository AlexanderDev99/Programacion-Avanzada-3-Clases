package Ejercicios3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

//CORECURSIVIDAD
public class MainApp {

    // Metodo fibonaci recursivo
    public static Integer fib(Integer n) {
        System.out.printf("fib: %d\n", n);
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
    // NOTA: La correcursivdad se basa basicamente en almacenar los calculos
    // anteriores y no volverlos a calcular
    // Esto con el fin de reutilizarlos.

    public static String fib4(Integer n) {
        if (n == 0) {
            return "0";
        } else if (n == 1) {
            return "0,1";
        } else if (n == 2) {
            return "0,1,1";
        } else {
            BigInteger x1 = BigInteger.ONE;
            BigInteger x2 = BigInteger.ONE;
            BigInteger f = BigInteger.ZERO;

            // cadena para almacenar los valores
            StringBuilder sb = new StringBuilder("0\n1\n1");
            for (int i = 3; i <= n.longValue(); i++) {
                f = x1.add(x2);
                x1 = x2;
                x2 = f;
                sb.append("\n" + f);
            }

            return sb.toString();
        }
    }

    // Metodo recursivo
    public int factorial(Integer n) {
        if (n == 0)
            return 1;
        return n * factorial(n - 1);
    }

    // Metodo Tail corecursivo
    // no deja una operacion pendiente.
    public static int fact(int n, int acc) {
        if (n == 0)
            return acc;
        return fact(n - 1, n * acc);
    }

    // FIbonaci usando Tail recursivo y correcursividad (almacenar calculos
    // anteriores)
    public static void fibAux(List<BigInteger> acc, BigInteger acc1, BigInteger acc2, BigInteger n) {
        System.out.printf("fib: %d\n", n);

        if (n.equals(BigInteger.ZERO)) {
            acc.add(BigInteger.ZERO);
        } else if (n.equals(BigInteger.ONE)) {
            acc.add(acc1.add(acc2));
        } else {
            /*
             * f = x1.add(x2);
             * x1 = x2;
             * x2 = f;
             */
            acc.add(acc1.add(acc2));
            fibAux(acc, acc2, acc1.add(acc2),
                    n.subtract(BigInteger.ONE));
        }

        /*
         * fibAux(acc, acc2, acc1.add(acc2),
         * n.subtract(BigInteger.ONE));
         */
    }

    public static List<BigInteger> fibTailRecursivo(Integer n) {
        List<BigInteger> lis = new ArrayList<>();
        fibAux(lis, BigInteger.ONE,
                BigInteger.ZERO,
                BigInteger.valueOf(n));
        return lis;
    }

    public static void main(String[] args) {

        Integer n = 40;
        Integer n2 = 1000;
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

        // Fibonaci con BigInteger
        System.out.println("\n-------> Fibonaci corecursivo BigInteger (n > 50)");
        for (int i = 0; i <= n; i++) {
            BigInteger r = fib3(BigInteger.valueOf(i));
            System.out.println("fib: " + r);
        }

        // Fibonaci optimizado
        System.out.println("\n-------> Fibonaci corecursivo Optimizado (n > 50)");
        String s = fib4(n);
        System.out.println(s);

        // Fibonacio tail recursivo
        /*
         * List<BigInteger> nums = fib(n2);
         * nums.stream().forEach(System.out::println);
         */

    }
}

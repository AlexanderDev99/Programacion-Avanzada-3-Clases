package Ejercicios;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Main {

    static final String patronNumber = "^\\+[0-9]{3}-[0-9]{10}$";
    static final Pattern numberPattern = Pattern.compile(patronNumber);

    static final String patronEmail = "^[a-zA-Z0-9_#$%/&]+@[a-z]{5}\\.[a-z]{3}";
    static final Pattern emailPattern = Pattern.compile(patronEmail);

    static Map<Integer, Long> cacheFactorial = new HashMap<>();

    public static Long factorial(Integer n) {
        if (n == 0 || n == 1)
            return 1L;
        if (cacheFactorial.containsKey(n)) {
            return cacheFactorial.get(n);
        }
        System.out.println("-> [CALCULANDO] Factorial de " + n);
        Long resultado = n * factorial(n - 1);
        cacheFactorial.put(n, resultado);
        return resultado;
        // System.out.println("\n--- TERCERA EJECUCIÓN (n=4) ---");
        // System.out.println("Resultado: " + factorial(4));
    }

    public static void main(String[] args) {

    }

}

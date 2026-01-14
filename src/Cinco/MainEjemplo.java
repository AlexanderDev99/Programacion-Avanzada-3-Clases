package Cinco;

import dos.Function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainEjemplo {

    public static void main(String[] args) {

        final double taxRate = 0.12;

        Function<Double, Double> addTax =
                price -> price + taxRate * price;

        List<Double> precios = Arrays.asList(1.2, 2.1, 3.1, 4.1);

        System.out.println("Resultado 2: " + callPrice(precios, addTax));

        // La division de dos numeros en java siempre esun entero

        // FUNCION INCOMPLETA
        Function<Integer, Integer> fn = x -> 1 / x; // en estecaso existeun problema en la division para cero

        //FUNCION COMPLETA
        Function<Integer, Optional<Integer>> fn2 = x -> { // Para solucionar este problema usamos Optional
            if (x == 0) { //Consideramos la excepcion
                return Optional.empty();
            } else {
                return Optional.of(1 / x);
            }
        };
        Optional<Integer> respuesta = fn2.apply(0);
        Optional<Integer> respuesta2 = fn2.apply(2);

        System.out.println(respuesta);
        System.out.println(respuesta2);

        // Pasandole los dos valores de la division
        Function<Integer, Function<Integer, Optional<Integer>>> curriedDivision = dividendo -> divisor -> {
            if (divisor == 0) {
                // Si el divisor es 0, devolvemos Optional.empty() para manejar la excepción.
                return Optional.empty();
            } else {
                // Si no es 0, realizamos la división y envolvemos el resultado en Optional.of().
                return Optional.of(dividendo / divisor);
            }

        };

        //##################### OTRO CASO #############################
        //Dada una cadena de caracteres devolver el primer caracter
        Function<String, Character> primerElemt = s -> s.charAt(0); // Si lacadena no tuviece caracteres existe una excepcion

        Function<String, Optional<Character>> primero = s -> {
            if (s == null || s.isEmpty()) {
                return Optional.empty();
            } else {
                return Optional.of(s.charAt(0));
            }
        };

        Optional<Character> respuesta3 = primero.apply(null);
        System.out.println(respuesta3);


    }

    public static List<Double> callPrice(List<Double> lista, Function<Double, Double> f) {
        List<Double> newList = new ArrayList<>();

        for (var it : lista) {
            newList.add(f.apply(it));
        }
        return newList;
    }


}

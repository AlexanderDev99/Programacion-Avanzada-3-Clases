package dos;
import java.util.List;

import static dos.Function. *;
public class MainHigerCompose {

    public static void main(String[] args) {

        Function<Integer, Double> f = x -> x * 1.2;
        Function<Double, Double> g = x -> x/2;
        //La declaracion Function.<Integer, Double,Double> es una manera exolicita de declarar que tipos de datos recibe la funcion
        Double respuesta = Function.<Integer, Double,Double>higerCompose()
                .apply(g)
                .apply(f)
                .apply(4);
        System.out.println(respuesta);

        // Ejercicio 1
        // Ingresa un string, calcula su longitud y luego le suma 2
        Function<String, Integer> f1 = x -> x.length();
        Function<Integer, Integer> g1 = x -> x + 2;

        Integer respuesta2 = Function.<String, Integer, Integer>higerCompose()
                .apply(g1)  // f
                .apply(f1)  // g
                .apply("Hola");
        System.out.println(respuesta2);

        //Ejercicio 2
        //Ingres un booleano, se compara entre verdadero y falso, se toma el primer caracter de la cadena.
        Function<Boolean, String> f2 = b -> b ? "Verdadero" : "Falso";
        Function<String, Character> g2 = s -> s.charAt(0);
        Character respuesta3 = Function.<Boolean, String, Character>higerCompose()
                .apply(g2)   // f
                .apply(f2)   // g
                .apply(true);

        System.out.println(respuesta3);  // Debe imprimir: V

        //EJERCICIO EN CLASE

        //Dada una lista de numeros filtrar los pares, a cada par multiplicarlo por 2 y luego sumarle 10
        Function<List<Integer>, List<Integer>> filtrarPares =
                lista -> lista.stream()
                        .filter(n -> n % 2 == 0)
                        .toList();

        Function<List<Integer>, List<Integer>> multiplicar2 =
                lista -> lista.stream()
                        .map(n -> n * 2)
                        .toList();

        Function<List<Integer>, List<Integer>> sumar10 =
                lista -> lista.stream()
                        .map(n -> n + 10)
                        .toList();

        Function<List<Integer>, List<Integer>> transformacion =
                Function.<List<Integer>, List<Integer>, List<Integer>>higerCompose()
                        .apply( sumar10 )                 // sumamos 10
                        .apply( multiplicar2 )            // Multiplicamos
                        .compose( filtrarPares );         // filtramos

        List<Integer> numeros = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> respuesta4 = transformacion.apply(numeros);

        System.out.println(respuesta4);

        // EJERCICIO EN CLASE 3
        //Ingresa un string, calcula su longitud y luego le suma 2.5
        Function<String, Integer> f3 = x -> x.length();
        Function<Integer, Double> g3 = x -> x + 2.5;

        Double respuesta5 = Function.<String, Integer, Double>higerCompose()
                .apply(g3)
                .apply(f3)
                .apply("Hola");
        System.out.println(respuesta5);

    }
}

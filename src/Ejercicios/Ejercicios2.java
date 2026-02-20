package com.programacion.ejercicios_02;

import com.programacion.tools.Effect;
import com.programacion.tools.Result;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;

import static com.programacion.tools.CollectionUtilities.*;

public class Ejercicios2 {

    //Definimos el verificador de numero
    static final String patronNumber = "^\\+[0-9]{3}-[0-9]{10}$";
    static final Pattern numberPattern = Pattern.compile(patronNumber);

    static final String patronEmail = "^[a-zA-Z0-9_#$%/&]+@[a-z]{5}\\.[a-z]{3}";
    static final Pattern emailPattern = Pattern.compile(patronEmail);

    public static void main(String[] args) {

        //EJERCICIOS DE REPASO PROGRAMACION FUNCIONAL

        //EJERCICIO 1: GENERAR UNA CONTRASEÑA ALEATORIA DE 10 CARATERES CON NUMEROS, LETRAS Y CARACTERES ESPECIALES.
        Random rd = new Random();
        List<Character> numeros = list('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        List<Character> caracteres = list('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j');
        List<Character> especiales = list('#', '$', '%', '/', '(', ')', '=', '@', '!', '?');
        List<Integer> tamañoContraseña = list(0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<List<Character>> passwordList = list(numeros, caracteres, especiales);

        //iteramos segun eltamaño de la contraseña y hacemos uso de varias listas e iteramos aleatoriamente dentro de cada lista para obtener una contraseña aleatoria de 10 digitos
        String contraseña = foldLeft(tamañoContraseña, "", acc -> actual ->
                acc + passwordList.get(rd.nextInt(passwordList.size())).get(rd.nextInt(10))
        );
        System.out.println("EJERCICIO 1");
        System.out.println(contraseña);

        //#############################################################################################
        //EJERCICIO 2: HACER UN PROGRAMA QUE REALIZE LA VERIFICACION DE UNA CONTRASEÑA Y CORREO
        Effect<String> exito = s -> System.out.println("El valor " + s + " es valido ");
        Effect<String> falla = s -> System.out.println("Error: " + s);

        System.out.println("EJERCICIO 2");
        final Function<String, Result<String>> validadorEmail = r ->
                (r == null) ? Result.failure("El correo ingresado no debe ser nulo") :
                        (r.length() == 0) ?
                                Result.failure("El correo no debe ser vacio") :
                                (emailPattern.matcher(r).matches()) ?
                                        Result.success(r)
                                        : Result.failure("El correo ingresado debe ser valido");
        validadorEmail.apply("edwin_ale&%xander98@gmail.com").bind(exito, falla);

        final Function<String, Result<String>> validadorTelefono = r ->
                (r == null) ? Result.failure("El numero ingresado no debe ser nulo") :
                        (r.length() == 0) ?
                                Result.failure("El numero no debe ser vacio") :
                                (numberPattern.matcher(r).matches()) ?
                                        Result.success(r)
                                        : Result.failure("El numero ingresado debe ser valido");
        validadorTelefono.apply("+543-0960615807").bind(exito, falla);

        //#############################################################################################
        //EJERCICIO 3: Usar map para obtener los cuadrados de una lista y luego imprimir con forEach
        List<Integer> numeros2 = list(1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> cuadrados = map(numeros2, x -> x * x);
        System.out.println("EJERCICIO 3");
        cuadrados.forEach(System.out::print);

        //#############################################################################################
        //EJERCICIO 4: Concatenar los elementos de una lista utilizando comas para separar cada número utilizar uno de los métodos folding
        List<Integer> numeros3 = list(1, 2, 3, 4, 5, 6, 7, 8, 9);
        String concatNumber = foldLeft(numeros3, "", acc -> actual -> {
            //si el acumulador esta vacio devolvemos "" + numero, caso contrario, acumulado + "," + actual
            //de esta manera eliminamos la coma al inicio o al finalsegunel caso
            return acc.isEmpty() ? acc + actual : acc + "," + actual;
        });

        System.out.println("\nEJERCICIO 4");
        System.out.println(concatNumber);

        //#############################################################################################
        //EJERCICIO 5: Dada una lista de nombres ["ana", "pedro", "luis"], genera una nueva lista donde todos estén en mayúsculas.
        List<String> nombres = list("ana", "pedro", "luis");
        //usando map
        List<String> mayusculasMap = map(nombres, x -> x.toUpperCase());
        //usando Fold
        List<String> mayusculasFold = foldLeft(nombres, list(), acc -> actual -> append(acc, actual.toUpperCase()));
        System.out.println("EJERCICIO 5");
        System.out.println("folding: " + mayusculasFold);
        System.out.println("Map: " + mayusculasMap);

        //#############################################################################################
        //EJERCICIO 6: Dada una lista de palabras, utiliza map para obtener una lista con la cantidad de letras que tiene cada palabra.
        List<String> palabras = list("mundo", "palabra", "hola", "ingenieria", "computación");

        //Usando Map
        List<String> numeroPalabrasMap = map(palabras, x -> "La palabra " + x + " tiene " + x.length() + " letras");
        //Usando Folding
        List<String> numeroPalabrasFold = foldLeft(palabras, list(), acc -> actual ->
                append(acc, "La palabra " + actual + " tiene " + actual.length() + " letras"));

        System.out.println("EJERCICIO 6");
        System.out.println("usando Map: " + numeroPalabrasMap);
        System.out.println("usando Fold: " + numeroPalabrasFold);

        //#############################################################################################
        //EJERCICIO 7: Dada una lista de números, obtén una nueva lista donde a cada número se le sume 1.
        List<Integer> numeros4 = list(1, 2, 3, 4, 5, 6, 7, 8, 9);

        //usando map
        List<Integer> numerosMasUnoMap = map(numeros4, x -> x + 1);
        //Usando fold
        List<Integer> numerosMasUnoFold = foldLeft(numeros4, list(), acc -> actual -> append(acc, actual + 1));

        System.out.println("EJERCICIO 7");
        System.out.println("usando Map: " + numerosMasUnoMap);
        System.out.println("usando Fold: " + numerosMasUnoFold);

        //#############################################################################################
        //EJERCICIO 8: Calcula el producto de todos los elementos de una lista de números.
        List<Integer> numeros5 = list(1, 2, 3, 4, 5, 6, 7, 8, 9);

        //no se puede usar map usamos fold
        Integer producto = foldLeft(numeros5, 1, acc -> actual -> acc * actual);
        System.out.println("EJERCICIO 8");
        System.out.println(producto);

        //#############################################################################################
        //EJERCICIO 9: Aunque ya tienes .size(), intenta recrear la funcionalidad de contar cuántos elementos hay en una lista usando foldLeft.
        List<Character> elementos = list('a', 'b', 'c', 'e', 'f', 'g', 'h', 'i');
        Integer tamaño = foldLeft(elementos, 0, acc -> actual -> acc + 1);

        System.out.println("EJERCICIO 8");
        System.out.println("El numero de elemntos es: " + tamaño);

        //#############################################################################################
        //EJERCICIO 10: Encuentra el número más grande de una lista usando foldLeft.
        List<Integer> numeros6 = list(12123, 5, 7, 9, 12233003, 5, 56, 365, 568, 2);

        Integer masGrande = foldLeft(numeros6, numeros6.get(0), acc -> actual -> {
                    return acc > actual ? acc : actual;
                }
        );
        System.out.println("EJERCICIO 8");
        System.out.println("El numero mas grande de la lista es: " + masGrande);

        //#############################################################################################
        //EJERCICIO 11: Intenta obtener una lista que solo contenga los números pares de una lista original. Como no tienes el método filter,
        //        // deberás usar foldLeft y un if dentro de la función para decidir si haces append del elemento o si devuelves el acumulador tal como estaba.

        List<Integer> numeros7 = list(1, 2, 3, 4, 5, 6);
        List<Integer> pares = foldLeft(numeros7, list(), acc -> actual -> {
            return actual % 2 == 0 ? append(acc, actual) : acc;
        });
        System.out.println(pares);

        //#############################################################################################
        //EJERCICIO 12: El método invertir ya existe en tu clase, pero intenta hacerlo tú mismo usando foldLeft. Pista: En lugar de
        // append (añadir al final), piensa en qué pasaría si usaras un método que añada al principio (prepend).
        List<Integer> numeros8 = list(1, 2, 3, 4, 5, 6);
        List<Integer> invertir = foldLeft(numeros8, list(), acc -> actual -> prepend(acc, actual));
        System.out.println(invertir);

        //#############################################################################################
        //EJERCICIO 13: tuvieras una lista de listas (ej. [[1,2], [3,4]]), usa foldLeft para convertirla en una sola lista plana [1,2,3,4].
        List<Integer> subLista1 = list(1, 2, 3);
        List<Integer> subLista2 = list(4, 5, 6);
        List<List<Integer>> listasDeListas = list(subLista1, subLista2);
        List<Integer> listaPlana = foldLeft(listasDeListas, list(), acc -> actual ->
                foldLeft(actual, acc, acumulador -> actual2 -> append(acumulador, actual2))
        );
        System.out.println(listaPlana);

        //#############################################################################################
        //EJERCICIO 14: Dada una cadena de texto (convertida a lista de caracteres), usa foldLeft para contar cuántas veces aparece una letra específica.
        String txt = "abac";
        List<Character> convertido = map(list(txt.split("")), s -> s.charAt(0));
        int repeticion = foldLeft(convertido, 0, acc -> actual -> {
            return actual == 'a' ? acc + 1 : acc;
        });
        System.out.println(repeticion);

        //#############################################################################################
        //EJERCICIO 15: dada una cadena devolver cuantas veces se repite cada letra.
        String frase = "Programación Avanzada tres";
        List<String> palabras2 = list("amarrillo", "rojo", "verde", "amarrillo", "azul", "gris", "amarrillo", "rojo");
        List<Character> caracteres3 = map(list(frase.split("")), s -> s.charAt(0));
        Map<String, Integer> valorInicial = new HashMap<>();

        Map<String, Integer> fecuencias = foldLeft(palabras2, valorInicial, acc -> actual -> {
            //creamos una copia para evitar efectos secundarios
            Map<String, Integer> copia = new HashMap<>(acc);

            //modificamos la copia, si existe suma 1 y si no agrega 1
            copia.put(actual, copia.getOrDefault(actual, 0) + 1);

            return copia;
        });

        System.out.println(fecuencias);
//#############################################################################################
        //EJERCICIO 16
        List<String> palabras4 = list("sol", "sal", "silla", "mesa", "mar", "mono");
        //salida esperada {s = {3 = [sol, sal]}, m ={4 = [mesa, mono], 3 = [mar]}}

        Map<Character, Map<Integer, List<String>>> inicial = new HashMap<>();

        Map<Character, Map<Integer, List<String>>> resultado2 = foldLeft(palabras4, inicial, acc -> actual -> {

            //Auxiliar para mantener inmutabilidad
            Map<Character, Map<Integer, List<String>>> copia = new HashMap<>(acc);

            //obtener la clave inicial
            char claveInicial = actual.charAt(0);

            //obtener la longitud de la palabra
            int longitud = actual.length();

            //obtener la lista de palabras
            Map<Integer, List<String>> lista = copia.getOrDefault(claveInicial, new HashMap<>());

            //obtener la lista de palabras
            List<String> lista2 = lista.getOrDefault(longitud, list());

            //agregar la palabra a la lista
            lista2 = append(lista2, actual);

            //agregar la lista a la copia
            lista.put(longitud, lista2);

            //agregar la lista a la copia
            copia.put(claveInicial, lista);

            return copia;
        });

        System.out.println(resultado2);


    }
}

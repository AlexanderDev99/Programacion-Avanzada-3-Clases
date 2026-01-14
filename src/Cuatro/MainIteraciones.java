package Cuatro;

import dos.Function;
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainIteraciones {

    public static void main(String[] args) {
        List<Integer> integerList = List.of(1,2,3,4);


        // Tomar cada uno de los valores de la ista y multiplicarlos por 1.2

        Function<List<Integer>, List<Double>> operacion = x -> {
            List<Double> newList = new ArrayList<>();
            for (Integer value : integerList ){
                newList.add(value * 1.2);
            }
            return newList;
        };
        List<Double> resultado = operacion.apply(integerList);
        System.out.println(resultado);

        //Considerar

        List<Integer> list = Arrays.asList(1,2,3,4,5); //asList no deja modificar el tamaño de la lista pero si los datos.
        //list.add(7); // no es valido
        list.set(4, 100); // es valido
        System.out.println(list);
        List<Integer> listDos = List.of(1,2,3,4,5); // List.of es de tipo inmutable, es decir, no permite modificar el tamaño ni los datos.
        // listDos.add(7); //No es valido
        // listDos.set(5, 100); //No es valido

    }

    public static <T> List<T> append(List<T> List, T t){
        List<T> ts = copy(List);
        ts.add(t);
        return ts;
    }

    public static <T> List<T> copy(List<T> List) {
        // Se usa el constructor de ArrayList que toma otra Collection (la lista original)
        // para crear una nueva ArrayList con los mismos elementos.
        return new ArrayList<>(List);
    }
}

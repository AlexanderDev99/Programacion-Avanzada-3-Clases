package Cinco;

import dos.Function;

public class Ejemplo {

    public static void method() {
        final double taxRate = 0.12; // para que sea una fucnion pura, en la funcion simpredebo saber que valor debe dar como resultado
        // si taxRate es final aseguramos esto caso contrario no.

        Function<Double,Double> addTax = price -> price + taxRate * price;
    }

}

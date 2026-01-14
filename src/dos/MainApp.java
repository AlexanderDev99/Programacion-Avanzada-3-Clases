package dos;

public class MainApp {
    public static void main(String[] args) {
        //y=(x+2)2
//        int y = cuadrado(doble(3));
//        Cuadrado x = new Cuadrado();
//        Doble d = new Doble();
//        Function fg = compsfg(x, d);
//        Function gf = andThemfg(x, d);
//        System.out.println("fg : " + fg.apply(5));
//        System.out.println("gf : " + gf.apply(5));

        //EJERCICIOS

        // 1. Calcula la longitud de una cadena de texto (String) y luego eleva ese número al cuadrado.

        Function<Integer, Integer> cuadrado = x -> x * x;

        Function<String, Integer> longitud = s -> s.length();

        Function<String, Integer> longitudCuadrada = cuadrado.compose(longitud);

        Integer resultado = longitudCuadrada.apply("Hola mundo");
        System.out.println(resultado);

        // 2. Convertir un Booleano a String

        Function<Boolean, String> mensaje = b -> b ? "El resultado final es VERDADERO." : "El resultado final es FALSO.";
        Function<Boolean, Boolean> negar = b -> !b;
        Function<Boolean, String> negarYReportar = mensaje.compose(negar);

        String resultado2 = negarYReportar.apply(true);
        System.out.println(resultado2);

        // 3. aplicar descuento a un valor y luego lo duplicamos

        Function<Double, Double> duplicar = x -> x * 2.0;

        Function<Double, Double> descontar = x -> x * 0.9;

        //Usamos compose para aplicar el descuento al valor y luego lo doblamos
        Function<Double, Double> descDuplicado = duplicar.compose(descontar);

        Double resultado3 = descDuplicado.apply(100.00);
        System.out.println(resultado3);

        // 4. Tomamos un String, extraemos el primer caracter,
        // y luego convertimos ese carácter a mayúscula.

        Function<Character, String> aMayusculaString = c -> String.valueOf(c).toUpperCase();

        Function<String, Character> obtenerInicial = s -> s.charAt(0);

        Function<String, String> inicialMayuscula = aMayusculaString.compose(obtenerInicial);

        String resultado4 = inicialMayuscula.apply("Computacion");

        System.out.println(resultado4);

        // 5. Tomamos dos números enteros, los sumamos
        // y luego concatenamos un String.

        Function<Integer, String> stringAgregado = num -> "Avanzada " + num.toString();

        Function<Integer, Integer> sumarDiez = x -> x + 0;

        Function<Integer, String> codigoDeSuma = stringAgregado.compose(sumarDiez);

        String resultado5 = codigoDeSuma.apply(3);

        System.out.println(resultado5);


    }

    public static int doble(int x) {
        return x * 2;
    }

    public static int cuadrado(int x) {
        return x * x;
    }

    public static Function compsfg(final Function x, final Function y) {
        return t -> x.apply(y.apply(t));
    }

    public static Function andThemfg(final Function y, final Function x) {
        return t -> x.apply(y.apply(t));
    }

}

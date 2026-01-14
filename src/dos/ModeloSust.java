package dos;

import java.util.Arrays;
import java.util.List;

public class ModeloSust {

    public static int sumar(int x, int y) {
        String msg = String.format("Retornando la suma de %s + %s =", x, y);
        log(msg);
        return x + y;
    }

    public static void log(String s) {
        System.out.println(s);
    }

    public static Ret sum(int x, int y) {
        Ret ret = new Ret();
        ret.msg = String.format("Retornando la suma de %s + %s =", x, y);
        ret.suma = x + y;
        return ret;

    }

    public static void main(String[] args) {
        int z = sumar(7, 5);
        System.out.println(z);
        System.out.println(12);

        Ret r = sum(7, 5);
        System.out.println(r);

        Ret res = new Ret();
        res.msg = "Retornando la suma de 7 + 5 =";
        res.suma = 12;
        System.out.println(res);

       /* List<Integer> datos = Arrays.asList(1, 2, 3, 4, 5);
        datos.stream().map(x -> x % 2 == 0).count();
        List<Integer> pares = datos.stream().filter(x -> x%2 == 0 ? true:false);
        System.out.println(pares);


        System.out.println("El numero de pares es = " + datos);*/

    }
}

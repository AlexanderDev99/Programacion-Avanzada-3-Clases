package Tres;

import dos.Function;

import java.util.List;

public class MianClosures {
    static final int[] contador = {0};

    public static void main(String[] args) {

        Run incrementar = con -> {
            System.out.println("contando " + con[0]++);
        };

        incrementar.run(contador);
        incrementar.run(contador);
        incrementar.run(contador);
        incrementar.run(contador);


        double tax = 0.09;
        Function<Double, Function<Double, Double>> addTax
                = taxRate -> price -> price + taxRate;
        System.out.println(addTax.apply(tax).apply(12.00));

        TaxComputer tc9 = new TaxComputer(0.09);
        double price1 = tc9.compute(12.0);
        double price2 = tc9.compute(10.0);

        Function<Double, Double> tc10 = addTax.apply(0.09);
        double price3 = tc10.apply(12.0);
        double price4 = tc10.apply(10.0);
        System.out.println(tc10);

    }
}

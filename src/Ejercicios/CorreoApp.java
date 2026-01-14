package Ejercicios;

import java.util.function.Function;
import java.util.regex.Pattern;

public class CorreoApp {

    static final String patron = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}";
    static final Pattern emailPattern = Pattern.compile(patron);

    static final String patronNum = "^\\+?[0-9]{10,15}$";
    static final Pattern numberPattern = Pattern.compile(patronNum);

    public static void main(String[] args) {

        Effect<String> success = s -> System.out.println("Correo de verificacion para el correo: " + s);
        Effect<String> failure = s -> System.out.println("ERROR: " + s);
        final Function<String, Result<String>> emailCheker = s ->
                s == null ? Result.failure("El correo no debe ser nulo")
                : s.length() == 0?
                Result.failure("Elcorreo no debeser vacio")
                :emailPattern.matcher(s).matches()?
                Result.success(s)
                : Result.failure("El correo no es valido");

        emailCheker.apply(null).bind(success,failure);

        Effect<String> successNumber = s -> System.out.println("El numero ingresado es valido: " + s);
        Effect<String> failureNumber = s -> System.out.println("ERROR: " + s);

        final Function<String, Result<String>> numberCheker = s ->
                s == null ? Result.failure("El numero no debe ser nulo")
                        : s.length() == 0?
                        Result.failure("El numero no debe ser vacio")
                        :numberPattern.matcher(s).matches()?
                        Result.success(s)
                        : Result.failure("El numero no es valido");
        numberCheker.apply("0960615807").bind(successNumber, failureNumber);



    }
}

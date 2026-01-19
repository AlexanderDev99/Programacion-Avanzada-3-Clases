package Ejercicios;

import java.util.function.Function;
import java.util.regex.Pattern;

public class EnviarCorreo {

    final String patron = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}";
    final Pattern emailPattern = Pattern.compile(patron);

    //FORMA IMPERATIVA
    private void enviarCorreo(String email) {
        System.out.println("Correo de verificacion enviado a " + email);
    }

    private void desplegarMensaje(String txt) {
        System.out.printf("ERROR: %s\\n", txt);
    }

    public void testEmail(String email) {

        //Si el email cumnple con el patron es valido
        if (emailPattern.matcher(email).matches()) {
            enviarCorreo(email);
        } else {
            desplegarMensaje(String.format("Email invalido", email));
        }
    }

    //FORMA FUCIONAL

    //Funcion para la verificacion de correo valido
    final Function<String, Resultado> emailChecker = s -> {
        if (s == null)
            return new Resultado.Fallido("El correo no puede ser Nulo");
        else if (s.length() == 0)
            return new Resultado.Fallido("El correo no puede estar vacio");
        else if (emailPattern.matcher(s).matches())
            return new Resultado.Exito();
        else
            return new Resultado.Fallido("Correo invalido");

    };

    //Funcion para enviar y desplegar el mensaje
    public Ejecutable testMailFuncional(String email) {
        Resultado res = emailChecker.apply(email);

//        if (res instanceof Resultado.Exito)
//            return () -> enviarCorreo(email);
//         else {
//            Resultado.Fallido error = (Resultado.Fallido) res;
//            return () -> desplegarMensaje(resul.getMensaje());
//        }

        //usando operador ternario
        return  (res instanceof Resultado.Exito) ? () -> enviarCorreo(email)
                : () -> desplegarMensaje(((Resultado.Fallido) res ).getMensaje());
    }
}

interface Resultado {

    public class Exito implements Resultado {

    }

    public class Fallido implements Resultado {
        private String mensaje;

        public Fallido(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }
    }


}



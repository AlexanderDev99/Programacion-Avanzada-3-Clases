package Ejercicios;

public class MainApp {


    public static void main(String[] args) {

        EnviarCorreo correo1 = new EnviarCorreo();
        correo1.testEmail("alexander@gmail.com");

        Ejecutable ex = correo1.testMailFuncional("edwin:@gmail.com");
        ex.exec();
    }



}

package Ejercicios;

public interface Ejecutable {
    void exec();
}

//class EjecutableOk implements Ejecutable{
//
//    @Override
//    public void exec(String txt) {
//        System.out.println("Correo de verificacion enviado a : " + txt);
//    }
//}
//
//class EjecutableFallido implements Ejecutable{
//
//    @Override
//    public void exec(String txt) {
//        System.out.printf("ERROR: %s\\n, ", txt);
//    }
//}

//NOTA: los metodos que imprimen los mensajes se ejecutan al final al ser metodos llamados mediante una clase
//esto permite cumplir con no producir efectos secundarios.
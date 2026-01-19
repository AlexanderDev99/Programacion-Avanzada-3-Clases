package Ejercicios2;

public class Persona {

    public String nombre;
    public int edad;

    Persona(String nombre, int edad){
        this.nombre = nombre;
        this.edad = edad;
    }

    @Override
    public String toString() { return nombre + " (" + edad + ")"; }
    
}

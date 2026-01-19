package Ejercicios2;

import static Ejercicios.CollectionUtilities.*;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        //EJERCICIO 1
        List<Persona> personas = list(
                new Persona("Ana", 15),
                new Persona("Juan", 25),
                new Persona("Pedro", 45),
                new Persona("Lucía", 12),
                new Persona("Marta", 41));

        ClasificadorPersonas listaPersonasEdad = new ClasificadorPersonas(list(), list(), list());

        ClasificadorPersonas resultado = foldLeft(personas, listaPersonasEdad, acc -> p -> new ClasificadorPersonas(
                // Condición para Menores
                (p.edad < 18) ? append(acc.menores, p) : acc.menores,
                // Condición para Mayores de 18
                (p.edad >= 18) ? append(acc.mayores18, p) : acc.mayores18,
                // Condición para Mayores de 40
                (p.edad > 40) ? append(acc.mayores40, p) : acc.mayores40));

       
        System.out.println("Menores de 18: " + resultado.menores);
        System.out.println("Mayores de 18: " + resultado.mayores18);
        System.out.println("Mayores de 40: " + resultado.mayores40);

        //EJERICIO 2
        List<String> nombresNuevos = list("user_1", "abc", "admin_2024", "error!");

        // Estado inicial
        ResultadoProceso inicial = new ResultadoProceso(list(), list());

        // Método principal de registro usando folding
        ResultadoProceso resultadoFinal = foldLeft(nombresNuevos, inicial, acc -> nombre -> 
            esValido(nombre) 
                ? new ResultadoProceso(
                    append(acc.logs, "LOG: Usuario registrado -> " + nombre), // 3. Registrar log
                    append(acc.usuariosRegistrados, nombre)                  // 2. Registrar usuario
                  )
                : new ResultadoProceso(
                    append(acc.logs, "LOG ERROR: Nombre inválido -> " + nombre),
                    acc.usuariosRegistrados
                  )
        );

        // Mostrar resultados finales
        System.out.println("Usuarios en BD: " + resultadoFinal.usuariosRegistrados);
        System.out.println("Historial de Logs:");
        resultadoFinal.logs.forEach(System.out::println); 
        // Nota: uso forEach aquí solo para imprimir el resultado final, 
        // el procesamiento fue puramente funcional con tus utilidades.

    }

    // Validar nombre según expresión regular
    public static boolean esValido(String nombre) {
        return nombre.matches("^[a-zA-Z0-9_]{4,12}$"); // Ejemplo: letras/números, 4-12 caracteres
    }
    

}

package Ejercicios2;

import java.util.List;

class ClasificadorPersonas {
    final List<Persona> menores;
    final List<Persona> mayores18;
    final List<Persona> mayores40;

    ClasificadorPersonas(List<Persona> menores, List<Persona> mayores18, List<Persona> mayores40) {
        this.menores = menores;
        this.mayores18 = mayores18;
        this.mayores40 = mayores40;
    }
}
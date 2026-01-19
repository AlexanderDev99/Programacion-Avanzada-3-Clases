package Ejercicios2;

import java.util.List;

class ResultadoProceso {
    final List<String> logs;
    final List<String> usuariosRegistrados;

    ResultadoProceso(List<String> logs, List<String> usuariosRegistrados) {
        this.logs = logs;
        this.usuariosRegistrados = usuariosRegistrados;
    }
}
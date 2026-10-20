
package com.example.gestor.dto;

public record TareaResponse(
        int id,
        String titulo,
        String prioridad,
        boolean completada,
        int proyectoId) {
}




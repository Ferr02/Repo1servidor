
package com.example.gestor.dto;

public record ProyectoResponse(
        int id,
        String nombre,
        String descripcion,
        boolean activo,
        int numeroDeIncidencias) {
}



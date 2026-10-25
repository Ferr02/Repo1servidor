
package com.example.gestor.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProyectoPatchRequest {

    @Pattern(
        regexp = "(?s)(?=.*\\S).{3,80}",
        message = "El nombre debe tener entre 3 y 80 caracteres y no estar vacío"
    )
    private String nombre;

    @Size(
        max = 500,
        message = "La descripción no puede superar los 500 caracteres"
    )
    private String descripcion;

    private Boolean activo;

    public ProyectoPatchRequest() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}


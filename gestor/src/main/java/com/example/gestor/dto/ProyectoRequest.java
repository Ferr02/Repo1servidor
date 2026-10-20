
package com.example.gestor.dto;

public class ProyectoRequest {

    private String nombre;
    private String descripcion;

    public ProyectoRequest() {
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
}


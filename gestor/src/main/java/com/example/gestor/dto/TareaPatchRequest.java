package com.example.gestor.dto;
import com.example.gestor.validacion.PrioridadValida;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class TareaPatchRequest {

    @Pattern(
        regexp = "(?s)(?=.*\\S).{3,120}",
        message = "El título debe tener entre 3 y 120 caracteres y no estar vacío"
    )
    private String titulo;

    @PrioridadValida
    private String prioridad;

    private Boolean completada;

    @Positive(message = "El proyectoId debe ser positivo")
    private Integer proyectoId;
    

    public TareaPatchRequest() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public Boolean getCompletada() {
        return completada;
    }

    public void setCompletada(Boolean completada) {
        this.completada = completada;
    }

    public Integer getProyectoId() {
    return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }
}

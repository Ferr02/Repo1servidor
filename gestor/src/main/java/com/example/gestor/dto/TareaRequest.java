package com.example.gestor.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class TareaRequest {


@NotBlank(message = "El título es obligatorio")
@Size(min = 3, max = 120, message = "El título debe tener entre 3 y 120 caracteres")
private String titulo;

@NotBlank(message = "La prioridad es obligatoria")
@Pattern(regexp = "baja|media|alta", message = "La prioridad debe ser baja, media o alta")
private String prioridad;

@NotNull(message = "El proyectoId es obligatorio")
@Positive(message = "El proyectoId debe ser positivo")
private Integer proyectoId;

public TareaRequest() {
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

public Integer getProyectoId() {
    return proyectoId;
}

public void setProyectoId(Integer proyectoId) {
    this.proyectoId = proyectoId;
}


}

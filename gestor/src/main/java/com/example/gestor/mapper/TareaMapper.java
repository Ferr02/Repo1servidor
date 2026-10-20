
package com.example.gestor.mapper;

import com.example.gestor.dto.TareaRequest;
import com.example.gestor.dto.TareaPatchRequest;
import com.example.gestor.dto.TareaResponse;
import com.example.gestor.model.Tarea;

import java.util.ArrayList;
import java.util.List;
import com.example.gestor.dto.TareaPutRequest;

public class TareaMapper {

    private TareaMapper() {
    }

    public static Tarea aModelo(TareaRequest peticion) {

        Tarea tarea = new Tarea();

        tarea.setTitulo(peticion.getTitulo());
        tarea.setPrioridad(peticion.getPrioridad());
        tarea.setCompletada(false);

        if (peticion.getProyectoId() != null) {
            tarea.setProyectoId(peticion.getProyectoId());
        }

        return tarea;
    }

    public static TareaResponse aRespuesta(Tarea tarea) {

        return new TareaResponse(
                tarea.getId(),
                tarea.getTitulo(),
                tarea.getPrioridad(),
                tarea.isCompletada(),
                tarea.getProyectoId()
        );
    }

    public static List<TareaResponse> aRespuestas(List<Tarea> tareas) {

        List<TareaResponse> respuestas = new ArrayList<>();

        for (Tarea tarea : tareas) {
            respuestas.add(aRespuesta(tarea));
        }

        return respuestas;
    }

    public static void aplicar(
            TareaPatchRequest cambios,
            Tarea tarea) {

        if (cambios.getTitulo() != null) {
            tarea.setTitulo(cambios.getTitulo());
        }

        if (cambios.getPrioridad() != null) {
            tarea.setPrioridad(cambios.getPrioridad());
        }

        if (cambios.getCompletada() != null) {
            tarea.setCompletada(cambios.getCompletada());
        }

        if (cambios.getProyectoId() != null) {
            tarea.setProyectoId(cambios.getProyectoId());
        }
    }

    public static void actualizarCompleto(
            TareaPutRequest peticion,
            Tarea tarea) {

        tarea.setTitulo(peticion.getTitulo());
        tarea.setPrioridad(peticion.getPrioridad());
        tarea.setCompletada(peticion.isCompletada());

        if (peticion.getProyectoId() != null) {
            tarea.setProyectoId(peticion.getProyectoId());
        }
    }


}



package com.example.gestor.mapper;

import com.example.gestor.dto.ProyectoRequest;
import com.example.gestor.dto.ProyectoPatchRequest;
import com.example.gestor.dto.ProyectoResponse;
import com.example.gestor.model.Proyecto;

import java.util.ArrayList;
import java.util.List;

public class ProyectoMapper {

    private ProyectoMapper() {
    }

    public static Proyecto aModelo(ProyectoRequest peticion) {

        Proyecto proyecto = new Proyecto();

        proyecto.setNombre(peticion.getNombre());
        proyecto.setDescripcion(peticion.getDescripcion());
        proyecto.setActivo(true);

        return proyecto;
    }

    public static ProyectoResponse aRespuesta(Proyecto proyecto) {

        return new ProyectoResponse(
                proyecto.getId(),
                proyecto.getNombre(),
                proyecto.getDescripcion(),
                proyecto.isActivo(),
                proyecto.getNumeroDeIncidencias()
        );
    }

    public static List<ProyectoResponse> aRespuestas(List<Proyecto> proyectos) {

        List<ProyectoResponse> respuestas = new ArrayList<>();

        for (Proyecto proyecto : proyectos) {
            respuestas.add(aRespuesta(proyecto));
        }

        return respuestas;
    }

    public static void aplicar(
            ProyectoPatchRequest cambios,
            Proyecto proyecto) {

        if (cambios.getNombre() != null) {
            proyecto.setNombre(cambios.getNombre());
        }

        if (cambios.getDescripcion() != null) {
            proyecto.setDescripcion(cambios.getDescripcion());
        }

        if (cambios.getActivo() != null) {
            proyecto.setActivo(cambios.getActivo());
        }
    }
}



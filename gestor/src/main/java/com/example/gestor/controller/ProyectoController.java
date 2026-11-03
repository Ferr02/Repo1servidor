
package com.example.gestor.controller;

import com.example.gestor.model.Proyecto;
import com.example.gestor.model.Tarea;

import com.example.gestor.dto.ProyectoRequest;
import com.example.gestor.dto.ProyectoPatchRequest;
import com.example.gestor.dto.ProyectoResponse;
import com.example.gestor.dto.TareaResponse;
import com.example.gestor.error.ConflictoException;
import com.example.gestor.mapper.ProyectoMapper;
import com.example.gestor.mapper.TareaMapper;
import com.example.gestor.memoria.MemoriaProyecto;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import com.example.gestor.error.ConflictoException;
import com.example.gestor.error.RecursoNoEncontradoException;


@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    private final List<Proyecto> proyectos;
    private final List<Tarea> tareas;
    private int siguienteId = 1;

    public ProyectoController(MemoriaProyecto memoria) {
        this.proyectos = memoria.getProyectos();
        this.tareas = memoria.getTareas();
    }

    @GetMapping("/{id}/tareas")
    public ResponseEntity<List<TareaResponse>> tareasDelProyecto(
            @PathVariable(name = "id") int id) {

        boolean existe = false;

        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == id) {
                existe = true;
                break;
            }
        }

        if (!existe) {
        throw new RecursoNoEncontradoException("proyecto", id);
    }

        List<Tarea> resultado = new ArrayList<>();

        for (Tarea tarea : tareas) {
            if (tarea.getProyectoId() == id) {
                resultado.add(tarea);
            }
        }

        return ResponseEntity.ok(TareaMapper.aRespuestas(resultado));
    }

    @GetMapping
    public List<ProyectoResponse> lista(
            @RequestParam(name = "activo", required = false) Boolean activo) {

        if (activo == null) {
            return ProyectoMapper.aRespuestas(proyectos);
        }

        List<Proyecto> resultado = new ArrayList<>();

        for (Proyecto proyecto : proyectos) {
            if (proyecto.isActivo() == activo) {
                resultado.add(proyecto);
            }
        }

        return ProyectoMapper.aRespuestas(resultado);
    }

    @GetMapping("/{id}")
    public ProyectoResponse obtener(@PathVariable int id) {

        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == id) {
                return ProyectoMapper.aRespuesta(proyecto);
            }
        }

        throw new RecursoNoEncontradoException("proyecto", id);
    }

    @PostMapping
    public ResponseEntity<ProyectoResponse> crear(
            @Valid @RequestBody ProyectoRequest peticion) {

            boolean existe = proyectos.stream()
                    .anyMatch(p -> p.getNombre() != null
                            && p.getNombre().trim()
                                    .equalsIgnoreCase(peticion.getNombre().trim()));

            if (existe) {
                throw new ConflictoException(
                        "Ya existe un proyecto con el nombre: "
                                + peticion.getNombre());
            }

        Proyecto proyecto = ProyectoMapper.aModelo(peticion);

        proyecto.setId(siguienteId);
        siguienteId++;

        proyectos.add(proyecto);

        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(proyecto.getId())
                .toUri();

        return ResponseEntity.created(ubicacion)
                .body(ProyectoMapper.aRespuesta(proyecto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoResponse> actualizar(
            @PathVariable int id,
            @Valid @RequestBody ProyectoRequest datos) {

        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == id) {

                proyecto.setNombre(datos.getNombre());
                proyecto.setDescripcion(datos.getDescripcion());

                return ResponseEntity.ok(
                        ProyectoMapper.aRespuesta(proyecto));
            }
        }

        throw new RecursoNoEncontradoException("proyecto", id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProyectoResponse> modificar(
            @PathVariable int id,
            @Valid @RequestBody ProyectoPatchRequest cambios) {

        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == id) {

                ProyectoMapper.aplicar(cambios, proyecto);

                return ResponseEntity.ok(
                        ProyectoMapper.aRespuesta(proyecto));
            }
        }

        throw new RecursoNoEncontradoException("proyecto", id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable int id) {

        proyectos.removeIf(proyecto -> proyecto.getId() == id);

        return ResponseEntity.noContent().build();
    }
}


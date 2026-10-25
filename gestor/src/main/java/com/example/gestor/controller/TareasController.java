
package com.example.gestor.controller;

import com.example.gestor.model.Tarea;
import com.example.gestor.dto.TareaPatchRequest;
import com.example.gestor.dto.TareaRequest;
import com.example.gestor.dto.TareaResponse;
import com.example.gestor.mapper.TareaMapper;
import com.example.gestor.memoria.MemoriaProyecto;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.ArrayList;
import com.example.gestor.dto.TareaPutRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tareas")
public class TareasController {

    private final List<Tarea> tareas;
    private int siguienteId = 1;

    public TareasController(MemoriaProyecto memoria) {
        this.tareas = memoria.getTareas();
    }

    @GetMapping
    public List<TareaResponse> lista(
            @RequestParam(name = "completada", required = false) Boolean completada) {

        if (completada == null) {
            return TareaMapper.aRespuestas(tareas);
        }

        List<Tarea> resultado = new ArrayList<>();

        for (Tarea tarea : tareas) {
            if (tarea.isCompletada() == completada) {
                resultado.add(tarea);
            }
        }

        return TareaMapper.aRespuestas(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TareaResponse> detalle(
            @PathVariable(name = "id") int id) {

        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                return ResponseEntity.ok(TareaMapper.aRespuesta(tarea));
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<TareaResponse> crear(
            @Valid @RequestBody TareaRequest peticion) {

        Tarea tarea = TareaMapper.aModelo(peticion);

        tarea.setId(siguienteId);
        siguienteId++;

        tareas.add(tarea);

        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarea.getId())
                .toUri();

        return ResponseEntity.created(ubicacion)
                .body(TareaMapper.aRespuesta(tarea));
    }


    @PutMapping("/{id}")
    public ResponseEntity<TareaResponse> actualizar(
            @PathVariable(name = "id") int id,
            @RequestBody TareaPutRequest datos) {

        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {

                TareaMapper.actualizarCompleto(datos, tarea);

                return ResponseEntity.ok(
                        TareaMapper.aRespuesta(tarea));
            }
        }

        return ResponseEntity.notFound().build();
    }


    @PatchMapping("/{id}")
    public ResponseEntity<TareaResponse> modificar(
            @PathVariable(name = "id") int id,
            @RequestBody TareaPatchRequest cambios) {

        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                TareaMapper.aplicar(cambios, tarea);

                return ResponseEntity.ok(TareaMapper.aRespuesta(tarea));
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable(name = "id") int id) {

        tareas.removeIf(tarea -> tarea.getId() == id);

        return ResponseEntity.noContent().build();
    }
}


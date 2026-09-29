package com.example.gestor.controller;

import com.example.gestor.model.Proyecto;
import com.example.gestor.model.Tarea;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.ResponseEntity;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/proyectos")
public class ProyectoController {

    private final List<Proyecto> proyectos = new ArrayList<>();
    private int siguienteId = 1;

    @GetMapping
    public List<Proyecto> lista(
            @RequestParam(name = "activo", required = false) Boolean activo) {

        if (activo == null) {
            return proyectos;
        }

        List<Proyecto> resultado = new ArrayList<>();

        for (Proyecto proyecto : proyectos) {
            if (proyecto.isActivo() == activo) {
                resultado.add(proyecto);
            }
        }

        return resultado;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proyecto> obtener(@PathVariable int id) {
        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == id) {
                return ResponseEntity.ok(proyecto);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Proyecto> crear(@RequestBody Proyecto proyecto) {

        proyecto.setId(siguienteId);
        siguienteId++;

        proyectos.add(proyecto);

        URI ubicacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(proyecto.getId())
                .toUri();

        return ResponseEntity.created(ubicacion).body(proyecto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proyecto> actualizar(
                @PathVariable int id,
                @RequestBody Proyecto datos) {

            for (int i = 0; i < proyectos.size(); i++) {
                if (proyectos.get(i).getId() == id) {
                    datos.setId(id);
                    proyectos.set(i, datos);
                    return ResponseEntity.ok(datos);
                }
            }

        return ResponseEntity.notFound().build();
    }

        @PatchMapping("/{id}")
    public ResponseEntity<Proyecto> modificar(
            @PathVariable int id,
            @RequestBody Proyecto cambios) {

        for (Proyecto proyecto : proyectos) {
            if (proyecto.getId() == id) {

                if (cambios.getNombre() != null) {
                    proyecto.setNombre(cambios.getNombre());
                }

                if (cambios.getDescripcion() != null) {
                    proyecto.setDescripcion(cambios.getDescripcion());
                }

                return ResponseEntity.ok(proyecto);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {

        proyectos.removeIf(proyecto -> proyecto.getId() == id);

        return ResponseEntity.noContent().build();
    }
}
package com.example.gestor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HolaController {
    @GetMapping("/hola")
    public String hola() {
        return "Hola, mundo. Te responde Ferro.";
    }
    @GetMapping("/estado")
    public String estado() {
        return "Servidor en funcionamiento";
    }
    @GetMapping("/compras/resumen")
    public String resumen() {
        return "Esta aplicació gestionarà les compres de material.";
    }
}
/*package com.example.gestor.dto;

import com.example.gestor.model.Responsable;

public record ResponsableResponse(String nombre, String email) {

    public static ResponsableResponse desde(Responsable responsable) {
        if (responsable == null) {
            return null;
        }

        return new ResponsableResponse(
                responsable.getNombre(),
                responsable.getEmail()
        );
    }
}*/
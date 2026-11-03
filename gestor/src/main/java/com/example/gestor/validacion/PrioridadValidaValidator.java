package com.example.gestor.validacion;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrioridadValidaValidator
implements ConstraintValidator<PrioridadValida, String> {


@Override
public boolean isValid(String prioridad, ConstraintValidatorContext context) {
    if (prioridad == null) {
        return true;
    }

    return prioridad.equals("baja")
            || prioridad.equals("media")
            || prioridad.equals("alta");
}


}

package org.accesodatos.hogwarts.mapper;

import org.accesodatos.hogwarts.dto.ProfesorDTO;
import org.accesodatos.hogwarts.model.Profesor;
import org.springframework.stereotype.Component;

@Component
public class ProfesorMapper {
    public ProfesorDTO toDto(Profesor profesor) {
        if (profesor == null) return null;

        ProfesorDTO dto = new ProfesorDTO();
        dto.setId(profesor.getIdProfesor());
        dto.setNombre(profesor.getNombre() + " " + profesor.getApellido());
        dto.setFechaInicio(profesor.getFechaInicio());

        if (profesor.getAsignatura() != null) {
            dto.setAsignatura(profesor.getAsignatura().getNombre());
        }

        return dto;
    }
}
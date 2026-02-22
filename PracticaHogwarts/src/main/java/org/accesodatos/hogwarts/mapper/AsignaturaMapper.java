package org.accesodatos.hogwarts.mapper;

import org.accesodatos.hogwarts.dto.AsignaturaDTO;
import org.accesodatos.hogwarts.model.Asignatura;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class AsignaturaMapper {
    public AsignaturaDTO toDto(Asignatura asignatura) {
        if (asignatura == null) return null;

        AsignaturaDTO dto = new AsignaturaDTO();
        dto.setId(asignatura.getIdAsignatura());
        dto.setNombre(asignatura.getNombre());
        dto.setAula(asignatura.getAula());
        dto.setObligatoria(asignatura.getObligatoria());

        if (asignatura.getProfesores() != null && !asignatura.getProfesores().isEmpty()) {
            String nombresProfesores = asignatura.getProfesores().stream()
                    .map(p -> p.getNombre() + " " + p.getApellido())
                    .collect(Collectors.joining(", "));
            dto.setProfesor(nombresProfesores);
        } else {
            dto.setProfesor("Sin profesor asignado");
        }

        return dto;
    }
}
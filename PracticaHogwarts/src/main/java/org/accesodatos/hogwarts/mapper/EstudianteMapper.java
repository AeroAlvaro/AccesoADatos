package org.accesodatos.hogwarts.mapper;

import org.accesodatos.hogwarts.dto.AsignaturaCalificacionDTO;
import org.accesodatos.hogwarts.dto.EstudianteDTO;
import org.accesodatos.hogwarts.model.Estudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class EstudianteMapper {

    @Autowired
    private MascotaMapper mascotaMapper;

    public EstudianteDTO toDto(Estudiante estudiante) {
        if (estudiante == null) return null;

        EstudianteDTO dto = new EstudianteDTO();
        dto.setId(estudiante.getIdEstudiante());
        dto.setNombre(estudiante.getNombre() + " " + estudiante.getApellido());
        dto.setAnyoCurso(estudiante.getAnyoCurso());
        dto.setFechaNacimiento(estudiante.getFechaNacimiento());

        if (estudiante.getCasa() != null) {
            dto.setCasa(estudiante.getCasa().getNombre());
        }

        dto.setMascota(mascotaMapper.toDto(estudiante.getMascota()));

        if (estudiante.getMatriculas() != null) {
            dto.setAsignaturas(estudiante.getMatriculas().stream()
                    .map(matricula -> {
                        AsignaturaCalificacionDTO acDto = new AsignaturaCalificacionDTO();
                        acDto.setAsignatura(matricula.getAsignatura().getNombre());
                        acDto.setCalificacion(matricula.getCalificacion());
                        return acDto;
                    })
                    .collect(Collectors.toList()));
        } else {
            dto.setAsignaturas(Collections.emptyList());
        }

        return dto;
    }
}
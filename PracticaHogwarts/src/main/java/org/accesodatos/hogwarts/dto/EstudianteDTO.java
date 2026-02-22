package org.accesodatos.hogwarts.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class EstudianteDTO {
    private Long id;
    private String nombre;
    private Integer anyoCurso;
    private LocalDate fechaNacimiento;
    private String casa;
    private MascotaDTO mascota;
    private List<AsignaturaCalificacionDTO> asignaturas;
}
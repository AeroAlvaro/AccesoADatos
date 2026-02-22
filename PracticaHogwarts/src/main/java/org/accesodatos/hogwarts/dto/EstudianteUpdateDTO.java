package org.accesodatos.hogwarts.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EstudianteUpdateDTO {
    @NotNull
    private Integer anyoCurso;

    @NotNull
    private LocalDate fechaNacimiento;

    private MascotaCreateDTO mascota;
}
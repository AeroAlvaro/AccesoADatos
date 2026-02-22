package org.accesodatos.hogwarts.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class EstudianteCreateDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;

    @NotNull
    @Max(value = 7, message = "El año no puede ser superior a 7")
    private Integer anyoCurso;

    @NotNull
    private LocalDate fechaNacimiento;

    @NotNull
    private Long casaId;

    private MascotaCreateDTO mascota;
}
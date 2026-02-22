package org.accesodatos.hogwarts.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MascotaCreateDTO {
    @NotBlank
    private String nombre;

    @NotBlank
    private String especie;
}
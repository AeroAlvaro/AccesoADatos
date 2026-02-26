package com.example.GymManager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservaDTO {
    private Long id;

    @NotBlank(message = "La actividad no puede estar vacía")
    private String actividad;

    @NotNull(message = "La fecha y hora son obligatorias")
    private LocalDateTime fechaHora;
}
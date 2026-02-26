package com.example.GymManager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaquillaDTO {
    private Long id;

    @NotBlank(message = "El número de taquilla es obligatorio")
    private String numero;

    private boolean estado;
}
package com.example.GymManager.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservaDTO {
    private Long id;
    private String actividad;
    private LocalDateTime fechaHora;
}
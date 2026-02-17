package com.example.GymManager.dto;

import lombok.Data;
import java.util.List;

@Data
public class SocioDTO {
    private Long id;
    private String nombre;
    private String email;

    private TaquillaDTO taquilla;
    private List<ReservaDTO> reservas;
}

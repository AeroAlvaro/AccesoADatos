package com.example.GymManager.service;

import com.example.GymManager.dto.SocioDTO;
import java.util.List;

public interface SocioService {
    List<SocioDTO> obtenerTodos();
    SocioDTO obtenerPorId(Long id);
    SocioDTO guardarSocio(SocioDTO socioDTO);
    SocioDTO actualizarSocio(Long id, SocioDTO socioDTO);
    void eliminarSocio(Long id);
}
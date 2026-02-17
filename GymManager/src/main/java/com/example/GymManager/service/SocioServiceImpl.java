package com.example.GymManager.service;

import com.example.GymManager.dto.ReservaDTO;
import com.example.GymManager.dto.SocioDTO;
import com.example.GymManager.dto.TaquillaDTO;
import com.example.GymManager.model.Reserva;
import com.example.GymManager.model.Socio;
import com.example.GymManager.model.Taquilla;
import com.example.GymManager.repository.SocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocioServiceImpl implements SocioService {

    @Autowired
    private SocioRepository socioRepository;

    @Override
    public List<SocioDTO> obtenerTodos() {
        return socioRepository.findAll().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public SocioDTO obtenerPorId(Long id) {
        Socio socio = socioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));
        return convertirADTO(socio);
    }

    @Override
    public SocioDTO guardarSocio(SocioDTO socioDTO) {
        Socio socio = convertirAEntidad(socioDTO);
        Socio socioGuardado = socioRepository.save(socio);
        return convertirADTO(socioGuardado);
    }

    @Override
    public SocioDTO actualizarSocio(Long id, SocioDTO socioDTO) {
        Socio socioExistente = socioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Socio no encontrado"));

        socioExistente.setNombre(socioDTO.getNombre());
        socioExistente.setEmail(socioDTO.getEmail());

        if (socioDTO.getTaquilla() != null) {
            Taquilla taquilla = new Taquilla();
            taquilla.setNumero(socioDTO.getTaquilla().getNumero());
            taquilla.setEstado(socioDTO.getTaquilla().isEstado());
            socioExistente.setTaquilla(taquilla);
        }

        Socio socioActualizado = socioRepository.save(socioExistente);
        return convertirADTO(socioActualizado);
    }

    @Override
    public void eliminarSocio(Long id) {
        socioRepository.deleteById(id);
    }

    private SocioDTO convertirADTO(Socio socio) {
        SocioDTO dto = new SocioDTO();
        dto.setId(socio.getId());
        dto.setNombre(socio.getNombre());
        dto.setEmail(socio.getEmail());

        if (socio.getTaquilla() != null) {
            TaquillaDTO taquillaDTO = new TaquillaDTO();
            taquillaDTO.setId(socio.getTaquilla().getId());
            taquillaDTO.setNumero(socio.getTaquilla().getNumero());
            taquillaDTO.setEstado(socio.getTaquilla().isEstado());
            dto.setTaquilla(taquillaDTO);
        }

        if (socio.getReservas() != null) {
            List<ReservaDTO> reservasDTO = socio.getReservas().stream().map(reserva -> {
                ReservaDTO rDto = new ReservaDTO();
                rDto.setId(reserva.getId());
                rDto.setActividad(reserva.getActividad());
                rDto.setFechaHora(reserva.getFechaHora());
                return rDto;
            }).collect(Collectors.toList());
            dto.setReservas(reservasDTO);
        }

        return dto;
    }

    private Socio convertirAEntidad(SocioDTO dto) {
        Socio socio = new Socio();
        socio.setNombre(dto.getNombre());
        socio.setEmail(dto.getEmail());

        if (dto.getTaquilla() != null) {
            Taquilla taquilla = new Taquilla();
            taquilla.setNumero(dto.getTaquilla().getNumero());
            taquilla.setEstado(dto.getTaquilla().isEstado());
            socio.setTaquilla(taquilla);
        }

        return socio;
    }
}
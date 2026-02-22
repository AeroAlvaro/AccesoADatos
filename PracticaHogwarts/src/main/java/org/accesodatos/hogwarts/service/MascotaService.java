package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.dto.MascotaDTO;
import org.accesodatos.hogwarts.mapper.MascotaMapper;
import org.accesodatos.hogwarts.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private MascotaMapper mascotaMapper;

    public List<MascotaDTO> obtenerTodas() {
        return mascotaRepository.findAll().stream()
                .map(mascotaMapper::toDto)
                .collect(Collectors.toList());
    }

    public void borrarMascota(Long id) {
        if (!mascotaRepository.existsById(id)) {
            throw new RuntimeException("Mascota no encontrada");
        }
        mascotaRepository.deleteById(id);
    }
}
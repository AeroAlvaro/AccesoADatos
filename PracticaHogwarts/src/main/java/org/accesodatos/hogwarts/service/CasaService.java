package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.dto.CasaDTO;
import org.accesodatos.hogwarts.mapper.CasaMapper;
import org.accesodatos.hogwarts.repository.CasaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.accesodatos.hogwarts.model.Casa;
import org.accesodatos.hogwarts.model.Estudiante;


@Service
public class CasaService {

    @Autowired
    private CasaRepository casaRepository;

    @Autowired
    private CasaMapper casaMapper;

    public List<CasaDTO> obtenerTodas() {
        return casaRepository.findAll().stream()
                .map(casaMapper::toDto)
                .collect(Collectors.toList());
    }

    public void borrarCasa(Long id) {
        Casa casa = casaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Casa no encontrada"));

        List<Estudiante> estudiantes = casa.getEstudiantes();
        if (estudiantes != null && !estudiantes.isEmpty()) {
            throw new RuntimeException("No se puede eliminar la casa porque tiene estudiantes asignados.");
        }

        casaRepository.delete(casa);
    }
}
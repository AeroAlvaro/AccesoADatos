package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.dto.AsignaturaDTO;
import org.accesodatos.hogwarts.mapper.AsignaturaMapper;
import org.accesodatos.hogwarts.repository.AsignaturaRepository;
import org.accesodatos.hogwarts.repository.EstudianteAsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.accesodatos.hogwarts.model.Asignatura;
import org.accesodatos.hogwarts.model.EstudianteAsignatura;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @Autowired
    private EstudianteAsignaturaRepository estudianteAsignaturaRepository;

    @Autowired
    private AsignaturaMapper asignaturaMapper;

    public List<AsignaturaDTO> obtenerTodas() {
        return asignaturaRepository.findAll().stream()
                .map(asignaturaMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void borrarAsignatura(Long id) {
        Asignatura asignatura = asignaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asignatura no encontrada"));

        List<EstudianteAsignatura> matriculas = estudianteAsignaturaRepository.findByAsignatura(asignatura);
        estudianteAsignaturaRepository.deleteAll(matriculas);

        asignaturaRepository.delete(asignatura);
    }
}
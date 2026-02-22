package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.dto.ProfesorDTO;
import org.accesodatos.hogwarts.mapper.ProfesorMapper;
import org.accesodatos.hogwarts.repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.accesodatos.hogwarts.model.Profesor;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private ProfesorMapper profesorMapper;

    public List<ProfesorDTO> obtenerTodos() {
        return profesorRepository.findAll().stream()
                .map(profesorMapper::toDto)
                .collect(Collectors.toList());
    }

    public void borrarProfesor(Long id) {
        Profesor profesor = profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));

        if (profesor.getCasa() != null) {
            throw new RuntimeException("No se puede eliminar el profesor porque es Jefe de la casa " + profesor.getCasa().getNombre());
        }

        profesorRepository.delete(profesor);
    }
}
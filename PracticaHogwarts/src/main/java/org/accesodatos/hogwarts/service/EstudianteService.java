package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.dto.EstudianteCreateDTO;
import org.accesodatos.hogwarts.dto.EstudianteDTO;
import org.accesodatos.hogwarts.dto.EstudianteUpdateDTO;
import org.accesodatos.hogwarts.mapper.EstudianteMapper;
import org.accesodatos.hogwarts.model.Casa;
import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.model.EstudianteAsignatura;
import org.accesodatos.hogwarts.model.Mascota;
import org.accesodatos.hogwarts.repository.CasaRepository;
import org.accesodatos.hogwarts.repository.EstudianteAsignaturaRepository;
import org.accesodatos.hogwarts.repository.EstudianteRepository;
import org.accesodatos.hogwarts.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private CasaRepository casaRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private EstudianteMapper estudianteMapper;

    @Autowired
    private EstudianteAsignaturaRepository estudianteAsignaturaRepository;

    public List<EstudianteDTO> obtenerTodos() {
        return estudianteRepository.findAll().stream()
                .map(estudianteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public EstudianteDTO crearEstudiante(EstudianteCreateDTO dto) {
        Casa casa = casaRepository.findById(dto.getCasaId())
                .orElseThrow(() -> new RuntimeException("Casa no encontrada"));

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setAnyoCurso(dto.getAnyoCurso());
        estudiante.setFechaNacimiento(dto.getFechaNacimiento());
        estudiante.setCasa(casa);

        Estudiante guardado = estudianteRepository.save(estudiante);

        if (dto.getMascota() != null) {
            Mascota mascota = new Mascota();
            mascota.setNombre(dto.getMascota().getNombre());
            mascota.setEspecie(dto.getMascota().getEspecie());
            mascota.setEstudiante(guardado);
            mascotaRepository.save(mascota);
            guardado.setMascota(mascota);
        }

        return estudianteMapper.toDto(guardado);
    }

    @Transactional
    public EstudianteDTO actualizarEstudiante(Long id, EstudianteUpdateDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        estudiante.setAnyoCurso(dto.getAnyoCurso());
        estudiante.setFechaNacimiento(dto.getFechaNacimiento());

        if (dto.getMascota() == null) {
            if (estudiante.getMascota() != null) {
                mascotaRepository.delete(estudiante.getMascota());
                estudiante.setMascota(null);
            }
        } else {
            if (estudiante.getMascota() != null) {
                Mascota mascotaExistente = estudiante.getMascota();
                mascotaExistente.setNombre(dto.getMascota().getNombre());
                mascotaExistente.setEspecie(dto.getMascota().getEspecie());
                mascotaRepository.save(mascotaExistente);
            } else {
                Mascota nuevaMascota = new Mascota();
                nuevaMascota.setNombre(dto.getMascota().getNombre());
                nuevaMascota.setEspecie(dto.getMascota().getEspecie());
                nuevaMascota.setEstudiante(estudiante);
                mascotaRepository.save(nuevaMascota);
                estudiante.setMascota(nuevaMascota);
            }
        }

        return estudianteMapper.toDto(estudianteRepository.save(estudiante));
    }

    @Transactional
    public void borrarEstudiante(Long id) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        List<EstudianteAsignatura> matriculas = estudianteAsignaturaRepository.findByEstudiante(estudiante);
        estudianteAsignaturaRepository.deleteAll(matriculas);

        if (estudiante.getMascota() != null) {
            mascotaRepository.delete(estudiante.getMascota());
        }

        estudianteRepository.delete(estudiante);
    }
}
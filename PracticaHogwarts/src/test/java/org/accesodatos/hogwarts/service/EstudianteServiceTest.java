package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.repository.EstudianteAsignaturaRepository;
import org.accesodatos.hogwarts.repository.EstudianteRepository;
import org.accesodatos.hogwarts.repository.MascotaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @Mock
    private EstudianteAsignaturaRepository estudianteAsignaturaRepository;

    @Mock
    private MascotaRepository mascotaRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    @Test
    void testExpulsarHarryPotter() {
        Long idHarry = 1L;
        Estudiante harry = new Estudiante();
        harry.setIdEstudiante(idHarry);
        harry.setNombre("Harry");
        harry.setApellido("Potter");

        when(estudianteRepository.findById(idHarry)).thenReturn(Optional.of(harry));
        when(estudianteAsignaturaRepository.findByEstudiante(harry)).thenReturn(new ArrayList<>());

        estudianteService.borrarEstudiante(idHarry);

        verify(estudianteRepository, times(1)).delete(harry);
    }
}
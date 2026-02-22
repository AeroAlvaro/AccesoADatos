package org.accesodatos.hogwarts.repository;

import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.model.Mascota;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EstudianteRepositoryTest {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private MascotaRepository mascotaRepository;

    @Test
    void testBorradoEnCascada() {
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Hermione");
        estudiante.setApellido("Granger");
        estudiante.setAnyoCurso(3);
        estudiante.setFechaNacimiento(LocalDate.of(1979, 9, 19));

        estudiante = estudianteRepository.save(estudiante);

        Mascota mascota = new Mascota();
        mascota.setNombre("Crookshanks");
        mascota.setEspecie("Gato");
        mascota.setEstudiante(estudiante);

        mascota = mascotaRepository.save(mascota);

        estudiante.setMascota(mascota);
        estudianteRepository.save(estudiante);

        estudianteRepository.delete(estudiante);

        Optional<Mascota> mascotaBorrada = mascotaRepository.findById(mascota.getIdMascota());
        assertThat(mascotaBorrada).isEmpty();
    }
}
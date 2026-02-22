package org.accesodatos.hogwarts.repository;

import org.accesodatos.hogwarts.model.Asignatura;
import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.model.EstudianteAsignatura;
import org.accesodatos.hogwarts.model.EstudianteAsignaturaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstudianteAsignaturaRepository extends JpaRepository<EstudianteAsignatura, EstudianteAsignaturaKey> {
    List<EstudianteAsignatura> findByEstudiante(Estudiante estudiante);
    List<EstudianteAsignatura> findByAsignatura(Asignatura asignatura);
}
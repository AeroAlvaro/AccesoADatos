package org.accesodatos.hogwarts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class EstudianteAsignaturaKey implements Serializable {

    @Column(name = "id_estudiante")
    private Long idEstudiante;

    @Column(name = "id_asignatura")
    private Long idAsignatura;
}
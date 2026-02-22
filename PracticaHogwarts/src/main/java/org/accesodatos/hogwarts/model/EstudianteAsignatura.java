package org.accesodatos.hogwarts.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "estudiante_asignatura")
public class EstudianteAsignatura {

    @EmbeddedId
    private EstudianteAsignaturaKey id;

    @ManyToOne
    @MapsId("idEstudiante")
    @JoinColumn(name = "id_estudiante")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("idAsignatura")
    @JoinColumn(name = "id_asignatura")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Asignatura asignatura;

    @Column(name = "calificacion")
    private Double calificacion;
}
package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@Data
@Entity
@Table(name = "asignatura")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignatura")
    private Long idAsignatura;

    @Column(name = "nombre_asignatura", unique = true, nullable = false, length = 100)
    private String nombre;

    private String aula;

    private Boolean obligatoria;

    @OneToMany(mappedBy = "asignatura")
    @JsonBackReference("profesor-asignatura")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Profesor> profesores = new ArrayList<>();

    @OneToMany(mappedBy = "asignatura")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<EstudianteAsignatura> inscripciones = new HashSet<>();
}
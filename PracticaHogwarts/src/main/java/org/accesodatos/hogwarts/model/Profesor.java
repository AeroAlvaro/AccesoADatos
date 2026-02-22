package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "profesor", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nombre", "apellido"})
})
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Long idProfesor;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    @JsonManagedReference("profesor-asignatura")
    private Asignatura asignatura;

    @OneToOne(mappedBy = "jefeCasa")
    @JsonBackReference("casa-profesor")
    @ToString.Exclude
    private Casa casa;
}
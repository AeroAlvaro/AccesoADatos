package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.SoftDelete;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@SoftDelete
@Table(name = "estudiante", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nombre", "apellido"})
})
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Long idEstudiante;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String apellido;

    @Column(name = "anyo_curso")
    private Integer anyoCurso;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_casa")
    @JsonBackReference("casa-estudiante")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Casa casa;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("estudiante-mascota")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Mascota mascota;

    @OneToMany(mappedBy = "estudiante")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<EstudianteAsignatura> matriculas = new HashSet<>();
}
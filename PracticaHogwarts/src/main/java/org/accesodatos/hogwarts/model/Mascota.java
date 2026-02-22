package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Long idMascota;

    @Column(name = "nombre_mascota", nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String especie;

    @OneToOne
    @JoinColumn(name = "id_estudiante")
    @JsonBackReference("estudiante-mascota")
    @ToString.Exclude
    private Estudiante estudiante;
}
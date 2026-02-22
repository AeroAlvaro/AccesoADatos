package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "casa")
public class Casa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_casa")
    private Long idCasa;

    @Column(name = "nombre_casa", unique = true, nullable = false, length = 50)
    private String nombre;

    @Column (nullable = false, length = 50)
    private String fundador;

    @Column (nullable = false, length = 50)
    private String fantasma;

    @OneToOne
    @JoinColumn(name = "id_jefe", nullable = false)
    @JsonManagedReference("casa-profesor")
    private Profesor jefeCasa;

    @OneToMany(mappedBy = "casa")
    @JsonManagedReference("casa-estudiante")
    private List<Estudiante> estudiantes;
}
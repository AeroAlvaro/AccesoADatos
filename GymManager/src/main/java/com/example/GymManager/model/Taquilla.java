package com.example.GymManager.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Taquilla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;

    private boolean estado;

    @OneToOne(mappedBy = "taquilla")
    @ToString.Exclude
    private Socio socio;
}
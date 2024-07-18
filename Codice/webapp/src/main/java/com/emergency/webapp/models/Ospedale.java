package com.emergency.webapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ospedale")
public class Ospedale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ospedale", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "nome_ospedale", nullable = false, length = 30)
    private String nomeOspedale;

    @Column(name = "latitudine_ospedale", nullable = false)
    private Double latitudineOspedale;

    @Column(name = "longitudine_ospedale", nullable = false)
    private Double longitudineOspedale;

    @Column(name = "disponibilita_ospedale", nullable = false)
    private Boolean disponibilitaOspedale = false;

    @OneToMany(mappedBy = "ospedale", cascade = CascadeType.ALL)
    private List<Emergenza> emergenzas;
}
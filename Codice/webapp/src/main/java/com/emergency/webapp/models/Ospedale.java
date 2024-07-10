package com.emergency.webapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    @Column(name = "nome_ospedale", nullable = false, length = 30)
    private String nomeOspedale;

    @NotNull
    @Column(name = "latitudine_ospedale", nullable = false)
    private Double latitudineOspedale;

    @NotNull
    @Column(name = "longitudine_ospedale", nullable = false)
    private Double longitudineOspedale;

    @NotNull
    @Column(name = "disponibilita_ospedale", nullable = false)
    private Boolean disponibilitaOspedale = false;

    @OneToMany(mappedBy = "ospedale", cascade = CascadeType.ALL)
    private List<Emergenza> emergenzas;
}
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
@Table(name = "aat")
public class Aat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aat", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "nome_aat", nullable = false, length = 30)
    private String nomeAat;

    @NotNull
    @Column(name = "latitudine_aat", nullable = false)
    private Double latitudineAat;

    @NotNull
    @Column(name = "longitudine_aat", nullable = false)
    private Double longitudineAat;

    @OneToMany(mappedBy = "aat", cascade = CascadeType.ALL)
    private List<Mezzo> mezzos;

    @OneToMany(mappedBy = "aat", cascade = CascadeType.ALL)
    private List<Squadra> squadras;
}
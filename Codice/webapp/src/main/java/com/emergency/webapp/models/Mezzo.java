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
@Table(name = "mezzo")
public class Mezzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mezzo", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "targa_mezzo", nullable = false, length = 30)
    private String targaMezzo;

    @Size(max = 30)
    @NotNull
    @Column(name = "tipo_mezzo", nullable = false, length = 30)
    private String tipoMezzo;

    @NotNull
    @Column(name = "stato_mezzo", nullable = false)
    private Character statoMezzo;

    @ManyToOne
    @JoinColumn(name="id_aatm", referencedColumnName = "id_aat")
    private Aat aat;

    @OneToMany(mappedBy = "mezzo", cascade = CascadeType.ALL)
    private List<Squadra> squadras;
}
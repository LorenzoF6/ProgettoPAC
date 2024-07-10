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
@Table(name = "squadra")
public class Squadra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_squadra", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "stato_squadra", nullable = false, length = 30)
    private String statoSquadra;

    @NotNull
    @Column(name = "disponibilita_squadra", nullable = false)
    private Boolean disponibilitaSquadra = false;

    @OneToMany(mappedBy = "squadra", cascade = CascadeType.ALL)
    private List<Volontario> volontarios;

    @OneToMany(mappedBy = "squadra", cascade = CascadeType.ALL)
    private List<Emergenza> emergenzas;

    @ManyToOne
    @JoinColumn(name="id_aats", referencedColumnName = "id_aat")
    private Aat aat;

    @ManyToOne
    @JoinColumn(name="id_mezzos", referencedColumnName = "id_mezzo")
    private Mezzo mezzo;
}
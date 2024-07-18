package com.emergency.webapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "emergenza")
public class Emergenza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_emergenza", nullable = false)
    private Integer id;

    @Column(name = "latitudine_emergenza", nullable = false)
    private Double latitudineEmergenza;

    @Column(name = "longitudine_emergenza", nullable = false)
    private Double longitudineEmergenza;

    @Column(name = "data_emergenza", nullable = false)
    private LocalDate dataEmergenza;

    @Column(name = "orario_emergenza", nullable = false)
    private LocalTime orarioEmergenza;

    @Size(max = 30)
    @Column(name = "motivo_emergenza", length = 30)
    private String motivoEmergenza;

    @Column(name = "codice_gravita_emergenza", nullable = false)
    private Character codgravitaEmergenza;

    @OneToMany(mappedBy = "emergenza", cascade = CascadeType.ALL)
    private List<Paziente> pazientes;

    @ManyToOne
    @JoinColumn(name="id_squadrae", referencedColumnName = "id_squadra")
    private Squadra squadra;

    @ManyToOne
    @JoinColumn(name="id_operatore118e", referencedColumnName = "user_operatore118")
    private Operatore118 operatore118;

    @ManyToOne
    @JoinColumn(name="id_ospedalee", referencedColumnName = "id_ospedale")
    private Ospedale ospedale;
}
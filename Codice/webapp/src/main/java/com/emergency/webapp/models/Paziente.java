package com.emergency.webapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "paziente")
public class Paziente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paziente", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "nome_paziente", nullable = false, length = 30)
    private String nomePaziente;

    @Size(max = 30)
    @Column(name = "cognome_paziente", nullable = false, length = 30)
    private String cognomePaziente;

    @Column(name = "eta_paziente", nullable = false)
    private Integer etaPaziente;

    @Column(name = "sesso_paziente", nullable = false)
    private Character sessoPaziente;

    @Column(name = "cosciente_paziente", nullable = false)
    private Boolean coscientePaziente = false;

    @Column(name = "respira_paziente", nullable = false)
    private Boolean respiraPaziente = false;

    @Size(max = 100)
    @Column(name = "patologie_paziente", nullable = false, length = 100)
    private String patologiePaziente;

    @ManyToOne
    @JoinColumn(name="id_emergenzap", referencedColumnName = "id_emergenza")
    private Emergenza emergenza;
}
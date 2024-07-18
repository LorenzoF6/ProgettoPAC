package com.emergency.webapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "volontario")
public class Volontario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_volontario", nullable = false)
    private Integer id;

    @Size(max = 30)
    @NotNull
    @Column(name = "nome_volontario", nullable = false, length = 30)
    private String nomeVolontario;

    @Size(max = 30)
    @NotNull
    @Column(name = "cognome_volontario", nullable = false, length = 30)
    private String cognomeVolontario;

    @Size(max = 30)
    @NotNull
    @Column(name = "user_volontario", nullable = false, length = 30)
    private String userVolontario;

    @Size(max = 30)
    @NotNull
    @Column(name = "pwd_volontario", nullable = false, length = 30)
    private String pwdVolontario;

    @Column(name ="ruolo_volontario")
    private String role;

    @ManyToOne
    @JoinColumn(name = "id_squadrav", referencedColumnName = "id_squadra")
    private Squadra squadra;



}
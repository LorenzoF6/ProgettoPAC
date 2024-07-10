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
@Table(name = "operatore118")
public class Operatore118 {
    @Id
    @Size(max = 30)
    @Column(name = "user_operatore118", nullable = false, length = 30)
    private String userOperatore118;

    @Size(max = 30)
    @NotNull
    @Column(name = "pwd_operatore118", nullable = false, length = 30)
    private String pwdOperatore118;

    @Size(max = 30)
    @NotNull
    @Column(name = "nome_operatore118", nullable = false, length = 30)
    private String nomeOperatore118;

    @Size(max = 30)
    @NotNull
    @Column(name = "cognome_operatore118", nullable = false, length = 30)
    private String cognomeOperatore118;

    @Column(name = "ruolo_operatore118")
    private String role;

    @OneToMany(mappedBy = "operatore118", cascade = CascadeType.ALL)
    private List<Emergenza> emergenzas;


}
package com.emergency.webapp.dtos;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmergenzaDTO {
    private Integer id;
    private Double latitudineEmergenza;
    private Double longitudineEmergenza;
    private LocalDate dataEmergenza;
    private LocalTime orarioEmergenza;
    private String motivoEmergenza;
    private Character codgravitaEmergenza;
    private List<PazienteDTO> pazientes;
    private SquadraDTO squadra;
    private Operatore118DTO operatore118;
    private OspedaleDTO ospedale;
}

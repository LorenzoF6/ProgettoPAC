package com.emergency.webapp.dtos;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SquadraDTO {
    private Integer id;
    private String statoSquadra;
    private Boolean disponibilitaSquadra;
    private AatDTO aat;
    private MezzoDTO mezzo;
    private List<VolontarioDTO> volontarios;
}

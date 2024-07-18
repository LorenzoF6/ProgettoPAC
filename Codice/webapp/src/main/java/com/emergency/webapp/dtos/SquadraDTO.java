package com.emergency.webapp.dtos;

import com.emergency.webapp.models.Emergenza;
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
    private Integer disponibilitaSquadra;
    private List<VolontarioDTO> volontarios;
    private AatDTO aat;
    private MezzoDTO mezzo;
}

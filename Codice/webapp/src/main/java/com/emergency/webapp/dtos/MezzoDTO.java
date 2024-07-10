package com.emergency.webapp.dtos;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MezzoDTO {
    private Integer id;
    private String targaMezzo;
    private String tipoMezzo;
    private Character statoMezzo;
    private AatDTO aat;
}

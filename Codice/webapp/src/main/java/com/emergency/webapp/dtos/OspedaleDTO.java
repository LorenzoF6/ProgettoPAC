package com.emergency.webapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OspedaleDTO {
    private Integer id;
    private String nomeOspedale;
    private Double latitudineOspedale;
    private Double longitudineOspedale;
    private Boolean disponibilitaOspedale;
}

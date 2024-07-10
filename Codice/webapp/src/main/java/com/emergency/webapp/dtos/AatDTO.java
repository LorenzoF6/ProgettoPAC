package com.emergency.webapp.dtos;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AatDTO {
    private Integer id;
    private String nomeAat;
    private Double latitudineAat;
    private Double longitudineAat;
}
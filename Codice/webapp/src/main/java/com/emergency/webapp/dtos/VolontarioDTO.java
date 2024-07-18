package com.emergency.webapp.dtos;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VolontarioDTO {
    private Integer id;
    private String nomeVolontario;
    private String cognomeVolontario;
    private String userVolontario;
    private String pwdVolontario;
    private String role;
    private SquadraDTO squadra;

}

package com.emergency.webapp.dtos;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PazienteDTO {
    private Integer id;
    private String nomePaziente;
    private String cognomePaziente;
    private Integer etaPaziente;
    private Character sessoPaziente;
    private Boolean coscientePaziente;
    private Boolean respiraPaziente;
    private String patologiePaziente;
    private EmergenzaDTO emergenza;
}

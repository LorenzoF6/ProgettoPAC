package com.emergency.webapp.controllers;

import com.emergency.webapp.dtos.EmergenzaDTO;
import com.emergency.webapp.dtos.VolontarioDTO;
import com.emergency.webapp.services.EmergenzaService;
import com.emergency.webapp.services.OspedaleService;
import com.emergency.webapp.services.VolontarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/api/volontari")
public class VolontarioController {

    @Autowired
    private VolontarioService volontarioService;

    @Autowired
    private EmergenzaService emergenzaService;

    @Autowired
    private OspedaleService ospedaleService;

    @GetMapping("/infoVolontarioSquadra") // Inizializzazione pagina turno-squadra e verifica emergenza disponibile
    public String getSquadraVolontario(Model model) {

        VolontarioDTO volontario = volontarioService.getVolontarioByUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<EmergenzaDTO> emergenzeDaGestire = emergenzaService.getEmergenzeDisponibili(volontario);

        String verificaEme;
        if(emergenzeDaGestire.isEmpty()){
             verificaEme = "Non Disponibile";
        }
        else{
            verificaEme = "Disponibile";
        }

        model.addAttribute("status",verificaEme);
        model.addAttribute("volontario", volontario);

        return "turno-squadra";
    }

    @GetMapping("/verificaEmergenza") // Visualizzazione emergenza disponibile
    public String verificaDispEmergenza(Model model) {

        VolontarioDTO volontario = volontarioService.getVolontarioByUser(SecurityContextHolder.getContext().getAuthentication().getName());
        List<EmergenzaDTO> emergenzeDaGestire = emergenzaService.getEmergenzeDisponibili(volontario);
        EmergenzaDTO emergenza = emergenzeDaGestire.getFirst();

        model.addAttribute("emergenza",emergenza);
        model.addAttribute("volontario", volontario);
        return "visualizzazione-volontari-emergenza";
    }


    @GetMapping("/gestioneEmergenza/{id}") // Inizializzazione pagina di gestione-volontari-emergenza e aggiornamento stati quando il volontario accetta
    public String gestioneVolontariEmergenza(@PathVariable("id") int id, Model model) {

        VolontarioDTO volontario = volontarioService.getVolontarioByUser(SecurityContextHolder.getContext().getAuthentication().getName());
        EmergenzaDTO emergenza = emergenzaService.getEmergenzaById(id);
        emergenzaService.setSquadraEmergenza(emergenza,
              volontarioService.getSquadraIdByUserVolontario(SecurityContextHolder.getContext().getAuthentication().getName())
        );

        EmergenzaDTO emergency =  emergenzaService.updateEmergenza(id,emergenza);


        int distanza = (int) ospedaleService.distanzaOspEme(emergency,emergency.getOspedale());
        model.addAttribute("volontario",volontario);
        model.addAttribute("ospedale",emergency.getOspedale());
        model.addAttribute("distanzaOE",distanza);
        model.addAttribute("emergenza",emergency);
        return "gestione-volontari-emergenza";
    }

    @PostMapping("/updateEmergenza/{id}") // Update dati dell'emergenza e aggiornamento stati
    public String updateEmergenza(@PathVariable("id") int id, @ModelAttribute EmergenzaDTO emergenzaDTO) {


        emergenzaService.updateEmergenza(id,emergenzaDTO);

        return "redirect:/api/pazienti/emergenzaNumero/{id}";
    }
}
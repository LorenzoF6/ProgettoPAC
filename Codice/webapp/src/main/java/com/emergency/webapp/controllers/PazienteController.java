package com.emergency.webapp.controllers;

import com.emergency.webapp.dtos.EmergenzaDTO;
import com.emergency.webapp.dtos.PazienteDTO;
import com.emergency.webapp.services.EmergenzaService;
import com.emergency.webapp.services.PazienteService;
import com.emergency.webapp.services.VolontarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/pazienti")
public class PazienteController {

    @Autowired
    private PazienteService pazienteService;

    @Autowired
    private EmergenzaService emergenzaService;

    @Autowired
    private VolontarioService volontarioService;

    @GetMapping("/emergenzaNumero/{idEmergenza}") // Pagina di inizializzazione per i pazienti
    public String gestionePaziente(@PathVariable("idEmergenza") int idEmergenza, Model model) {

        model.addAttribute("idEmergenza", idEmergenza);
        model.addAttribute("paziente", new PazienteDTO());
        return "aggiungi-paziente";
    }

    @PostMapping("/addPaziente/{idEmergenza}") // Aggiunta paziente all'emergenza
    public String addPaziente(@PathVariable("idEmergenza") int idEmergenza, @ModelAttribute("paziente") PazienteDTO pazienteDTO, Model model) {

        if (pazienteDTO.getNomePaziente() != null && !pazienteDTO.getNomePaziente().isEmpty()) {
            EmergenzaDTO emergenzaDTO = emergenzaService.getEmergenzaById(idEmergenza);
            pazienteService.setEmergenzaToPaziente(emergenzaDTO, pazienteDTO);
            PazienteDTO savedPaziente = pazienteService.createPaziente(pazienteDTO);

            System.out.println("Paziente nome e id emergenza a cui è riferito:" + savedPaziente.getNomePaziente() + savedPaziente.getEmergenza().getId());

        }

        model.addAttribute("idEmergenza", idEmergenza);
        model.addAttribute("paziente", new PazienteDTO());
        return "aggiungi-paziente";
    }

    @PostMapping("/save/{idEmergenza}") //salvataggio e attivazione sistema di alert
    public String savePaziente(@PathVariable("idEmergenza") int idEmergenza) {

        //volontarioService.getVolontarioByUser(SecurityContextHolder.getContext().getAuthentication().getName());

        return "redirect:/api/volontari/infoVolontarioSquadra";
    }
}
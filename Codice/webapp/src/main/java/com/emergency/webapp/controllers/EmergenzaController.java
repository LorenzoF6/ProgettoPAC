package com.emergency.webapp.controllers;

import com.emergency.webapp.dtos.*;
import com.emergency.webapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/api/emergenza")
public class EmergenzaController{

    @Autowired
    private EmergenzaService emergenzaService;

    @Autowired
    private OspedaleService ospedaleService;

    @Autowired
    private Operatore118Service operatore118Service;

    @Autowired
    private VolontarioService volontarioService;


    @GetMapping("/all") //Mostra tutte le emergenze in emergenza
    public String getAllEmergenze(Model model) {

        List<EmergenzaDTO> emergencyList = emergenzaService.getAllEmergenze();
        Operatore118DTO operatore = operatore118Service.getOperatore118ByUser(
                SecurityContextHolder.getContext().getAuthentication().getName());

        model.addAttribute("emergenze", emergencyList);
        model.addAttribute("operatore",operatore);
        return "emergenza";
    }

    @GetMapping("/gestioneEmergenza") // Inzializzazione pagina in gestione-emergenza
    public String gestioneEmergenza(Model model) {

        EmergenzaDTO emergency = emergenzaService.createEmergenza(new EmergenzaDTO());

        model.addAttribute("emergenza", emergency);
        model.addAttribute("ospedali", ospedaleService.getAllOspedali());
        return "gestione-emergenza";
    }


    @PostMapping("/addData") // Update dati e logica di scelta dell'ospedale
    public String addEmergencyData(@ModelAttribute("emergenza") EmergenzaDTO emergenzaDTO, Model model) {

        operatore118Service.setUserOperatore118(SecurityContextHolder.getContext().getAuthentication().getName(), emergenzaDTO);
        EmergenzaDTO emergency = emergenzaService.updateEmergenza(emergenzaDTO.getId(),emergenzaDTO);
        List<VolontarioDTO> volontari = volontarioService.getVolontariSortedByDistance(
              emergenzaDTO.getLatitudineEmergenza(), emergenzaDTO.getLongitudineEmergenza());
        List<OspedaleDTO> ospedali = ospedaleService.getOspedaliSortedByDistance(
                emergenzaDTO.getLatitudineEmergenza(), emergenzaDTO.getLongitudineEmergenza());
        if (!ospedali.isEmpty()) {
            emergenzaDTO.setOspedale(ospedali.getFirst());
            emergency = emergenzaService.updateEmergenza(emergenzaDTO.getId(), emergenzaDTO);
            emergency.setSquadra(new SquadraDTO());
        }

        model.addAttribute("listaVolontari",volontari);
        model.addAttribute("volontarioConsigliato", volontari.getFirst());
        model.addAttribute("listaOspedali",ospedali);
        model.addAttribute("emergenza", emergency);
        return "gestione-emergenza";
    }

    @GetMapping("/reportEmergenza/{id}") // Generazione del report dettagliato dell'emergenza
    public String reportEmergenza(@PathVariable int id,Model model){

        EmergenzaDTO ReportEmergenza = emergenzaService.getEmergenzaById(id);

        model.addAttribute("emergenza", ReportEmergenza);
        return "report-emergenza";
    }

    @GetMapping("/generaPDF/{id}") // Generazione del pdf relativo al report dell'emergenza selezionata
    public ResponseEntity<?> generaPDF(@PathVariable int id){

        byte[] pdfContents = emergenzaService.generatePdfReport(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "report-emergenza.pdf");

        return new ResponseEntity<>(pdfContents, headers, HttpStatus.OK);
    }
}





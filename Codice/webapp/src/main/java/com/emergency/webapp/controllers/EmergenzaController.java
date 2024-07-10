package com.emergency.webapp.controllers;

import com.emergency.webapp.dtos.EmergenzaDTO;
import com.emergency.webapp.dtos.Operatore118DTO;
import com.emergency.webapp.dtos.OspedaleDTO;
import com.emergency.webapp.dtos.SquadraDTO;
import com.emergency.webapp.models.Operatore118;
import com.emergency.webapp.services.EmergenzaService;
import com.emergency.webapp.services.Operatore118Service;
import com.emergency.webapp.services.OspedaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/api/emergenza")
public class EmergenzaController {

    @Autowired
    private EmergenzaService emergenzaService;

    @Autowired
    private OspedaleService ospedaleService;

    @Autowired
    private Operatore118Service operatore118Service;


    @GetMapping("/all")
    public String getAllEmergenze(Model model) {
        List<EmergenzaDTO> emergenze = emergenzaService.getAllEmergenze();
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        Operatore118DTO operatore118 = operatore118Service.getOperatore118ByUser(aut.getName());
        model.addAttribute("emergenze", emergenze);
        model.addAttribute("operatore",operatore118);
        return "emergenza";
    }

    @GetMapping("/addEmergenza")
    public String showGestioneEmergenza(Model model) {
        model.addAttribute("emergenza", new EmergenzaDTO());
        return "gestione-emergenza";
    }

    @PostMapping("/calculate-nearest-hospital")
    public String calculateNearestHospital(@ModelAttribute("emergenza") EmergenzaDTO emergenzaDTO, Model model) {
        double latitude = emergenzaDTO.getLatitudineEmergenza();
        double longitude = emergenzaDTO.getLongitudineEmergenza();
        List<OspedaleDTO> nearestOspedale = ospedaleService.getOspedaliSortedByDistance(latitude, longitude);
        emergenzaDTO.setOspedale(nearestOspedale.getFirst());
        model.addAttribute("emergenza", emergenzaDTO);
        return "gestione-emergenza";
    }

    /*
    @GetMapping("/addEmergenza")
    public String showGestioneEmergenza(Model model) {
        List<OspedaleDTO> ospedali = ospedaleService.getAllOspedali();
        model.addAttribute("ospedali", ospedali);
        model.addAttribute("emergenza", new EmergenzaDTO());
        return "gestione-emergenza";
    }

    @GetMapping("/calculate-distances")
    @ResponseBody
    public List<OspedaleDTO> calculateDistances(@RequestParam double latitude, @RequestParam double longitude) {
        return ospedaleService.getOspedaliSortedByDistance(latitude, longitude);
    }*/

    @PostMapping
    public String createEmergenza(@ModelAttribute EmergenzaDTO emergenzaDTO) {
        emergenzaDTO.setSquadra(new SquadraDTO());
        emergenzaService.createEmergenza(emergenzaDTO);
        return "redirect:/api/emergenze/all";
    }



}



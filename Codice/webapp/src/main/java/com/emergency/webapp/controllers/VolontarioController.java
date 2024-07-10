package com.emergency.webapp.controllers;


import com.emergency.webapp.dtos.VolontarioDTO;
import com.emergency.webapp.services.VolontarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/infoVolontarioSquadra")
    public String getSquadraVolontario(Model model) {
        Authentication aut = SecurityContextHolder.getContext().getAuthentication();
        VolontarioDTO volontario = volontarioService.getVolontarioByUser(aut.getName());
        model.addAttribute("volontario", volontario);
        return "turno-squadra";
    }

}
package com.emergency.webapp.controllers;

import com.emergency.webapp.dtos.MezzoDTO;
import com.emergency.webapp.services.MezzoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/mezzo")
public class MezzoController {

    @Autowired
    private MezzoService mezzoService;

    @GetMapping("/all")
    public String getAllMezzi(Model model) {
        List<MezzoDTO> mezzi = mezzoService.getAllMezzi();
        model.addAttribute("mezzi", mezzi);
        return "stato-mezzi";
    }
}

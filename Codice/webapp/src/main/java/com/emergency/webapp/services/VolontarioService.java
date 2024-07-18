package com.emergency.webapp.services;


import com.emergency.webapp.controllers.VolontarioController;
import com.emergency.webapp.dtos.EmergenzaDTO;
import com.emergency.webapp.dtos.OspedaleDTO;
import com.emergency.webapp.dtos.SquadraDTO;
import com.emergency.webapp.dtos.VolontarioDTO;
import com.emergency.webapp.exceptions.ResourceNotFoundException;
import com.emergency.webapp.models.Emergenza;
import com.emergency.webapp.models.Ospedale;
import com.emergency.webapp.models.Volontario;
import com.emergency.webapp.repositories.VolontarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolontarioService {

    @Autowired
    private VolontarioRepository volontarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public VolontarioDTO getVolontarioByUser(String userVolontario) {
        Volontario volontario = volontarioRepository.findByUserVolontario(userVolontario)
                .orElseThrow(() -> new ResourceNotFoundException("Volontario not found with user: " + userVolontario));
        return modelMapper.map(volontario, VolontarioDTO.class);
    }

    // Questo metodo restituisce la squadra ID del volontario
    public SquadraDTO getSquadraIdByUserVolontario(String userVolontario) {
        Volontario volontario = volontarioRepository.findByUserVolontario(userVolontario)
                .orElseThrow(() -> new ResourceNotFoundException("Volontario not found with user: " + userVolontario));
        return modelMapper.map(volontario.getSquadra(), SquadraDTO.class);
    }
    // Metodo per calcolare la distanza utilizzando la formula di Haversine
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Raggio della terra in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public List<VolontarioDTO> getVolontariSortedByDistance(double latitudineEmergenza, double longitudineEmergenza) {
        List<Volontario> volontari = volontarioRepository.findAll();
        return volontari.stream()
                .filter(volontario -> volontario.getSquadra().getDisponibilitaSquadra() == 1)
                .filter(volontario -> volontario.getUserVolontario().contains("V"))
                .filter(volontario -> volontario.getSquadra().getStatoSquadra().contains("SEDE"))
               // .filter(volontario -> calculateDistance(latitudineEmergenza,longitudineEmergenza,
                //      volontario.getSquadra().getAat().getLatitudineAat(),volontario.getSquadra().getAat().getLongitudineAat()) < 40)
                .sorted(Comparator.comparingDouble(volontario -> calculateDistance(
                                latitudineEmergenza, longitudineEmergenza,
                    volontario.getSquadra().getAat().getLatitudineAat(), volontario.getSquadra().getAat().getLongitudineAat()
                        ))
                )
                .map(volontario -> modelMapper.map(volontario, VolontarioDTO.class))
                .collect(Collectors.toList());
    }


}
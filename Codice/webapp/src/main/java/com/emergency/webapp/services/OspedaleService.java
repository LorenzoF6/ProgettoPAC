package com.emergency.webapp.services;


import com.emergency.webapp.dtos.EmergenzaDTO;
import com.emergency.webapp.dtos.OspedaleDTO;
import com.emergency.webapp.models.Ospedale;
import com.emergency.webapp.repositories.OspedaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OspedaleService {

    @Autowired
    private OspedaleRepository ospedaleRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<OspedaleDTO> getAllOspedali() {
        List<Ospedale> ospedales = ospedaleRepository.findAll();
        return ospedales.stream()
                .map(ospedale -> modelMapper.map(ospedale, OspedaleDTO.class))
                .collect(Collectors.toList());
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

    public List<OspedaleDTO> getOspedaliSortedByDistance(double latitude, double longitude) {
        List<Ospedale> ospedali = ospedaleRepository.findAll();
        return ospedali.stream()
                .filter(Ospedale::getDisponibilitaOspedale)
                .sorted(Comparator.comparingDouble(ospedale -> calculateDistance(
                        latitude, longitude,
                        ospedale.getLatitudineOspedale(),
                        ospedale.getLongitudineOspedale())))
                .map(ospedale -> modelMapper.map(ospedale, OspedaleDTO.class))
                .collect(Collectors.toList());
    }

    public double distanzaOspEme(EmergenzaDTO emergenzaDTO,OspedaleDTO ospedaleDTO){
        return calculateDistance(
                emergenzaDTO.getLatitudineEmergenza(),
                emergenzaDTO.getLongitudineEmergenza(),
                ospedaleDTO.getLatitudineOspedale(),
                ospedaleDTO.getLongitudineOspedale()
        );
    }

}
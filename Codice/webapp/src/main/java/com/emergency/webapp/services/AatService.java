package com.emergency.webapp.services;


import com.emergency.webapp.dtos.AatDTO;
import com.emergency.webapp.exceptions.ResourceNotFoundException;
import com.emergency.webapp.models.Aat;
import com.emergency.webapp.repositories.AatRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AatService {

    @Autowired
    private AatRepository aatRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AatDTO getAatById(Integer id) {
        Aat aat = aatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aat not found"));
        return modelMapper.map(aat, AatDTO.class);
    }

    public List<AatDTO> getAllAats() {
        List<Aat> aats = aatRepository.findAll();
        return aats.stream()
                .map(aat -> modelMapper.map(aat, AatDTO.class))
                .collect(Collectors.toList());
    }

    public AatDTO createAat(AatDTO aatDTO) {
        Aat aat = modelMapper.map(aatDTO, Aat.class);
        Aat savedAat = aatRepository.save(aat);
        return modelMapper.map(savedAat, AatDTO.class);
    }

    public AatDTO updateAat(Integer id, AatDTO aatDTO) {
        Aat existingAat = aatRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Aat not found"));
        modelMapper.map(aatDTO, existingAat);
        Aat updatedAat = aatRepository.save(existingAat);
        return modelMapper.map(updatedAat, AatDTO.class);
    }

    public void deleteAat(Integer id) {
        aatRepository.deleteById(id);
    }

    //Service per US29
    // Calcola la distanza in chilometri tra due punti usando la formula dell'haversine
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // convert to kilometers
    }

    public AatDTO findNearestAat(double latitude, double longitude) {
        List<Aat> aatList = aatRepository.findAll();
        if (aatList.isEmpty()) {
            throw new ResourceNotFoundException("No AAT found");
        }

        Aat nearestAat = null;
        double minDistance = Double.MAX_VALUE;

        for (Aat aat : aatList) {
            double distance = calculateDistance(latitude, longitude, aat.getLatitudineAat(), aat.getLongitudineAat());
            if (distance < minDistance) {
                minDistance = distance;
                nearestAat = aat;
            }
        }

        if (nearestAat == null) {
            throw new ResourceNotFoundException("No AAT found");
        }

        return modelMapper.map(nearestAat, AatDTO.class);
    }
}
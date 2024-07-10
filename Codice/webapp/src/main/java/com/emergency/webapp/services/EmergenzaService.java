package com.emergency.webapp.services;

import com.emergency.webapp.dtos.EmergenzaDTO;
import com.emergency.webapp.exceptions.ResourceNotFoundException;
import com.emergency.webapp.models.Emergenza;
import com.emergency.webapp.repositories.EmergenzaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmergenzaService {

    @Autowired
    private EmergenzaRepository emergenzaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EmergenzaDTO getEmergenzaById(Integer id) {
        Emergenza emergenza = emergenzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emergenza not found"));
        return modelMapper.map(emergenza, EmergenzaDTO.class);
    }

    public List<EmergenzaDTO> getAllEmergenze() {
        List<Emergenza> emergenze = emergenzaRepository.findAll();
        return emergenze.stream()
                .map(emergenza -> modelMapper.map(emergenza, EmergenzaDTO.class))
                .collect(Collectors.toList());
    }

    public EmergenzaDTO createEmergenza(EmergenzaDTO emergenzaDTO) {
        Emergenza emergenza = modelMapper.map(emergenzaDTO, Emergenza.class);
        Emergenza savedEmergenza = emergenzaRepository.save(emergenza);
        return modelMapper.map(savedEmergenza, EmergenzaDTO.class);
    }

    public EmergenzaDTO updateEmergenza(Integer id, EmergenzaDTO emergenzaDTO) {
        Emergenza existingEmergenza = emergenzaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emergenza not found"));
        modelMapper.map(emergenzaDTO, existingEmergenza);
        Emergenza updatedEmergenza = emergenzaRepository.save(existingEmergenza);
        return modelMapper.map(updatedEmergenza, EmergenzaDTO.class);
    }

    public void deleteEmergenza(Integer id) {
        emergenzaRepository.deleteById(id);
    }


}
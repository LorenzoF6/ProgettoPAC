package com.emergency.webapp.services;

import com.emergency.webapp.dtos.EmergenzaDTO;
import com.emergency.webapp.dtos.PazienteDTO;
import com.emergency.webapp.models.Paziente;
import com.emergency.webapp.repositories.PazienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PazienteService {

    @Autowired
    private PazienteRepository pazienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PazienteDTO createPaziente(PazienteDTO pazienteDTO) {
        Paziente paziente = modelMapper.map(pazienteDTO, Paziente.class);
        Paziente savedPaziente = pazienteRepository.save(paziente);
        return modelMapper.map(savedPaziente, PazienteDTO.class);
    }

    public void setEmergenzaToPaziente(EmergenzaDTO emergenzaDTO, PazienteDTO pazienteDTO) {
        pazienteDTO.setEmergenza(emergenzaDTO);
    }
}
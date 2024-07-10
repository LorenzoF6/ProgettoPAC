package com.emergency.webapp.services;


import com.emergency.webapp.dtos.PazienteDTO;
import com.emergency.webapp.exceptions.ResourceNotFoundException;
import com.emergency.webapp.models.Paziente;
import com.emergency.webapp.repositories.PazienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PazienteService {

    @Autowired
    private PazienteRepository pazienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PazienteDTO getPazienteById(Integer id) {
        Paziente paziente = pazienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paziente not found with id " + id));
        return modelMapper.map(paziente, PazienteDTO.class);
    }

    public List<PazienteDTO> getAllPazienti() {
        List<Paziente> pazientes = pazienteRepository.findAll();
        return pazientes.stream()
                .map(paziente -> modelMapper.map(paziente, PazienteDTO.class))
                .collect(Collectors.toList());
    }

    public PazienteDTO createPaziente(PazienteDTO pazienteDTO) {
        Paziente paziente = modelMapper.map(pazienteDTO, Paziente.class);
        Paziente savedPaziente = pazienteRepository.save(paziente);
        return modelMapper.map(savedPaziente, PazienteDTO.class);
    }

    public PazienteDTO updatePaziente(Integer id, PazienteDTO pazienteDTO) {
        Paziente existingPaziente = pazienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paziente not found with id " + id));
        modelMapper.map(pazienteDTO, existingPaziente);
        Paziente updatedPaziente = pazienteRepository.save(existingPaziente);
        return modelMapper.map(updatedPaziente, PazienteDTO.class);
    }

    public void deletePaziente(Integer id) {
        if (!pazienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paziente not found with id " + id);
        }
        pazienteRepository.deleteById(id);
    }
}
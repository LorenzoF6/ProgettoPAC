package com.emergency.webapp.services;


import com.emergency.webapp.dtos.SquadraDTO;
import com.emergency.webapp.exceptions.ResourceNotFoundException;
import com.emergency.webapp.models.Squadra;
import com.emergency.webapp.repositories.SquadraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SquadraService {

    @Autowired
    private SquadraRepository squadraRepository;

    @Autowired
    private ModelMapper modelMapper;

    public SquadraDTO getSquadraById(Integer id) {
        Squadra squadra = squadraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Squadra not found with id " + id));
        return modelMapper.map(squadra, SquadraDTO.class);
    }

    public List<SquadraDTO> getAllSquadre() {
        List<Squadra> squadras = squadraRepository.findAll();
        return squadras.stream()
                .map(squadra -> modelMapper.map(squadra, SquadraDTO.class))
                .collect(Collectors.toList());
    }

    public SquadraDTO createSquadra(SquadraDTO squadraDTO) {
        Squadra squadra = modelMapper.map(squadraDTO, Squadra.class);
        Squadra savedSquadra = squadraRepository.save(squadra);
        return modelMapper.map(savedSquadra, SquadraDTO.class);
    }

    public SquadraDTO updateSquadra(Integer id, SquadraDTO squadraDTO) {
        Squadra existingSquadra = squadraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Squadra not found with id " + id));
        modelMapper.map(squadraDTO, existingSquadra);
        Squadra updatedSquadra = squadraRepository.save(existingSquadra);
        return modelMapper.map(updatedSquadra, SquadraDTO.class);
    }

    public void deleteSquadra(Integer id) {
        if (!squadraRepository.existsById(id)) {
            throw new ResourceNotFoundException("Squadra not found with id " + id);
        }
        squadraRepository.deleteById(id);
    }

    public boolean isSquadraDisponibile(Integer id) {
        Squadra squadra = squadraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Squadra not found with id " + id));
        return squadra.getDisponibilitaSquadra();
    }
}
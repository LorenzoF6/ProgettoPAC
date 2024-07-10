package com.emergency.webapp.services;


import com.emergency.webapp.dtos.VolontarioDTO;
import com.emergency.webapp.exceptions.ResourceNotFoundException;
import com.emergency.webapp.models.Volontario;
import com.emergency.webapp.repositories.VolontarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolontarioService {

    @Autowired
    private VolontarioRepository volontarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    public VolontarioDTO getVolontarioById(Integer id) {
        Volontario volontario = volontarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volontario not found with id " + id));
        return modelMapper.map(volontario, VolontarioDTO.class);
    }

    public List<VolontarioDTO> getAllVolontarios() {
        List<Volontario> volontarios = volontarioRepository.findAll();
        return volontarios.stream()
                .map(volontario -> modelMapper.map(volontario, VolontarioDTO.class))
                .collect(Collectors.toList());
    }

    public VolontarioDTO createVolontario(VolontarioDTO volontarioDTO) {
        Volontario volontario = modelMapper.map(volontarioDTO, Volontario.class);
        Volontario savedVolontario = volontarioRepository.save(volontario);
        return modelMapper.map(savedVolontario, VolontarioDTO.class);
    }

    public VolontarioDTO updateVolontario(Integer id, VolontarioDTO volontarioDTO) {
        Volontario existingVolontario = volontarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Volontario not found with id " + id));
        modelMapper.map(volontarioDTO, existingVolontario);
        Volontario updatedVolontario = volontarioRepository.save(existingVolontario);
        return modelMapper.map(updatedVolontario, VolontarioDTO.class);
    }

    public void deleteVolontario(Integer id) {
        if (!volontarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Volontario not found with id " + id);
        }
        volontarioRepository.deleteById(id);
    }

    public VolontarioDTO getVolontarioByUser(String userVolontario) {
        Volontario volontario = volontarioRepository.findByUserVolontario(userVolontario)
                .orElseThrow(() -> new ResourceNotFoundException("Volontario not found with user: " + userVolontario));
        VolontarioDTO volontarioDTO = modelMapper.map(volontario, VolontarioDTO.class);
        return volontarioDTO;
    }

    // Questo metodo restituisce la squadra ID del volontario
    public Integer getSquadraIdByUserVolontario(String userVolontario) {
        Volontario volontario = volontarioRepository.findByUserVolontario(userVolontario)
                .orElseThrow(() -> new ResourceNotFoundException("Volontario not found with user: " + userVolontario));
        return volontario.getSquadra().getId();
    }
}
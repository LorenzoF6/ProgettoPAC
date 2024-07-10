package com.emergency.webapp.services;

import com.emergency.webapp.dtos.Operatore118DTO;
import com.emergency.webapp.exceptions.ResourceNotFoundException;
import com.emergency.webapp.models.Operatore118;
import com.emergency.webapp.repositories.Operatore118Repository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Operatore118Service {

    @Autowired
    private Operatore118Repository operatore118Repository;

    @Autowired
    private ModelMapper modelMapper;

    public Operatore118DTO getOperatore118ByUser(String user) {
        Operatore118 operatore = operatore118Repository.findById(user)
                .orElseThrow(() -> new ResourceNotFoundException("Operatore118 not found with user: " + user));
        return modelMapper.map(operatore, Operatore118DTO.class);
    }

    public List<Operatore118DTO> getAllOperatori118() {
        List<Operatore118> operatori = operatore118Repository.findAll();
        return operatori.stream()
                .map(operatore -> modelMapper.map(operatore, Operatore118DTO.class))
                .collect(Collectors.toList());
    }

    public Operatore118DTO createOperatore118(Operatore118DTO operatoreDTO) {
        Operatore118 operatore = modelMapper.map(operatoreDTO, Operatore118.class);
        Operatore118 savedOperatore = operatore118Repository.save(operatore);
        return modelMapper.map(savedOperatore, Operatore118DTO.class);
    }

    public Operatore118DTO updateOperatore118(String user, Operatore118DTO operatoreDTO) {
        Operatore118 existingOperatore = operatore118Repository.findById(user)
                .orElseThrow(() -> new ResourceNotFoundException("Operatore118 not found with user: " + user));
        modelMapper.map(operatoreDTO, existingOperatore);
        Operatore118 updatedOperatore = operatore118Repository.save(existingOperatore);
        return modelMapper.map(updatedOperatore, Operatore118DTO.class);
    }

    public void deleteOperatore118(String user) {
        Operatore118 operatore = operatore118Repository.findById(user)
                .orElseThrow(() -> new ResourceNotFoundException("Operatore118 not found with user: " + user));
        operatore118Repository.delete(operatore);
    }
}
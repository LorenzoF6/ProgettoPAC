package com.emergency.webapp.services;

import com.emergency.webapp.dtos.EmergenzaDTO;
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

    public void setUserOperatore118(String userOperatore118, EmergenzaDTO emergenzaDTO) {
        emergenzaDTO.setOperatore118(getOperatore118ByUser(userOperatore118));
    }


}
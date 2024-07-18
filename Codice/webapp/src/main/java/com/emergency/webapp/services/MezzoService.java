package com.emergency.webapp.services;

import com.emergency.webapp.dtos.MezzoDTO;
import com.emergency.webapp.exceptions.ResourceNotFoundException;
import com.emergency.webapp.models.Mezzo;
import com.emergency.webapp.repositories.MezzoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MezzoService {

    @Autowired
    private MezzoRepository mezzoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<MezzoDTO> getAllMezzi() {
        List<Mezzo> mezzos = mezzoRepository.findAll();
        return mezzos.stream()
                .map(mezzo -> modelMapper.map(mezzo, MezzoDTO.class))
                .collect(Collectors.toList());
    }
}
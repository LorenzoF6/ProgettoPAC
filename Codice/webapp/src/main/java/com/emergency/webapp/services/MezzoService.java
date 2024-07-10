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

    public MezzoDTO getMezzoById(Integer id) {
        Mezzo mezzo = mezzoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mezzo not found with id " + id));
        return modelMapper.map(mezzo, MezzoDTO.class);
    }

    public List<MezzoDTO> getAllMezzi() {
        List<Mezzo> mezzos = mezzoRepository.findAll();
        return mezzos.stream()
                .map(mezzo -> modelMapper.map(mezzo, MezzoDTO.class))
                .collect(Collectors.toList());
    }

    public MezzoDTO createMezzo(MezzoDTO mezzoDTO) {
        Mezzo mezzo = modelMapper.map(mezzoDTO, Mezzo.class);
        Mezzo savedMezzo = mezzoRepository.save(mezzo);
        return modelMapper.map(savedMezzo, MezzoDTO.class);
    }

    public MezzoDTO updateMezzo(Integer id, MezzoDTO mezzoDTO) {
        Mezzo existingMezzo = mezzoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mezzo not found with id " + id));
        modelMapper.map(mezzoDTO, existingMezzo);
        Mezzo updatedMezzo = mezzoRepository.save(existingMezzo);
        return modelMapper.map(updatedMezzo, MezzoDTO.class);
    }

    public void deleteMezzo(Integer id) {
        if (!mezzoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Mezzo not found with id " + id);
        }
        mezzoRepository.deleteById(id);
    }
}
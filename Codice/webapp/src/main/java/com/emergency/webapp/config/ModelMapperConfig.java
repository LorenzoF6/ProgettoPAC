package com.emergency.webapp.config;

import com.emergency.webapp.dtos.SquadraDTO;
import com.emergency.webapp.models.Squadra;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setPropertyCondition(Conditions.isNotNull());

        modelMapper.createTypeMap(SquadraDTO.class, Squadra.class)
                .addMappings(mapper -> mapper.skip(Squadra::setEmergenzas));

        return modelMapper;
    }

}
package com.emergency.webapp.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        //configureCustomMappings(modelMapper);
        return modelMapper;
    }

    /*private void configureCustomMappings(ModelMapper modelMapper) {
        // Aggiungi eventuali configurazioni custom per il mapping
        // Esempio:
        // modelMapper.typeMap(SourceClass.class, DestinationClass.class).addMappings(mapper -> {
        //     mapper.map(SourceClass::getSourceField, DestinationClass::setDestinationField);
        // });
    }*/
}
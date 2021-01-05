package org.keumann.template.config;

import org.keumann.template.domain.TimeRecord;
import org.keumann.template.dto.TimeRecordDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.config.Configuration.AccessLevel;

import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addMappings(new PropertyMap<TimeRecord, TimeRecordDto.DetailResponse>() {
            @Override
            protected void configure() {
                map().setStudyDate(source.getStudyDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                map().setStudyTypeName(source.getStudyType().getName());
            }
        });

        return modelMapper;
    }

}

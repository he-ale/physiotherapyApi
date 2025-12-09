package com.fldsmdfr.domainPatiens.adapters;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import com.fldsmdfr.domainPatiens.dto.PatientIdNameDto;
import com.fldsmdfr.domainPatiens.dto.PatientRegister;
import com.fldsmdfr.domainPatiens.dto.PatientResponse;
import com.fldsmdfr.domainPatiens.dto.PatientUpdate;
import com.fldsmdfr.domainPatiens.models.Patient;
import com.fldsmdfr.domainPatiens.models.utilities.Gender;
import com.fldsmdfr.domainPatiens.models.utilities.StatusPatient;

@Component
public class PatientMapper {
    private final ModelMapper modelMapper;

    public PatientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

        modelMapper.getConfiguration().setSkipNullEnabled(true);

        Converter<List<String>, String> listToString = new Converter<>() {
            @Override
            public String convert(MappingContext<List<String>, String> context) {
                List<String> source = context.getSource();
                if (source == null || source.isEmpty()) return null; // o ""
                return String.join(", ", source);
            }
        };

        Converter<String, List<String>> stringToList = new Converter<>() {
            @Override
            public List<String> convert(MappingContext<String, List<String>> context) {
                String source = context.getSource();
                if (source == null || source.isBlank()) return Collections.emptyList();
                return Arrays.asList(source.split(",\\s*"));
            }
        };

        modelMapper.typeMap(PatientRegister.class, Patient.class)
            .addMappings(mapper -> {
                mapper.map(src->src.getGender()!=null ? Gender.valueOf(src.getGender()) : null, Patient::setGender);
                
                mapper.using(listToString).map(PatientRegister::getMedicalConditions, Patient::setMedicalConditions);
                
                mapper.skip(Patient::setStatus);
            });

        modelMapper.typeMap(PatientUpdate.class, Patient.class)
            .addMappings(mapper -> {
                mapper.map(src->src.getGender()!=null ? Gender.valueOf(src.getGender()) : null, Patient::setGender);
                
                mapper.map(src->src.getStatus()!=null ? StatusPatient.valueOf(src.getStatus()) : null, Patient::setStatus);
                
                mapper.using(listToString).map(PatientUpdate::getMedicalConditions, Patient::setMedicalConditions);
                
                
            });
            
        modelMapper.typeMap(Patient.class, PatientResponse.class)
            .addMappings(mapper -> {
                mapper.using(stringToList).map(Patient::getMedicalConditions, PatientResponse::setMedicalConditions);
            });
    }

    public Patient toEntity(PatientRegister dto) {
        return modelMapper.map(dto, Patient.class);
    }

    public Patient toUpdateEntity(PatientUpdate dto) {
        return modelMapper.map(dto, Patient.class);
    }

    public PatientResponse toResponse(Patient patient) {
        PatientResponse response = modelMapper.map(patient, PatientResponse.class);
        return response;
    }

    public List<PatientIdNameDto> toPatientIdNameDto(List<Patient> patients) {
        return patients.stream()
                .map(patient -> new PatientIdNameDto(patient.getId(), patient.getName()))
                .toList();
    }
}

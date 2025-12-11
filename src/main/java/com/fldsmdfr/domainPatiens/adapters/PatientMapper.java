package com.fldsmdfr.domainPatiens.adapters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.fldsmdfr.common.dto.PageResponse;
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
                if (source == null || source.isEmpty()) return "";
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
                // mapper.skip(PatientResponse::setId);
                mapper.skip(PatientResponse::setMedicalConditions);
                // mapper.skip(PatientResponse::setId);
                // mapper.skip(PatientResponse::setName);
                // mapper.skip(PatientResponse::setEmail);
                // mapper.skip(PatientResponse::setBirthDate);
                // mapper.skip(PatientResponse::setPhone);
                // mapper.skip(PatientResponse::setGender);
                // mapper.skip(PatientResponse::setAddress);
                // mapper.skip(PatientResponse::setStatus);
                // mapper.skip(PatientResponse::setCreatedAt);
                // mapper.skip(PatientResponse::setUpdatedAt);
                mapper.map(Patient::getId, PatientResponse::setId);
                mapper.map(Patient::getName, PatientResponse::setName);
                mapper.map(Patient::getEmail, PatientResponse::setEmail);
                mapper.map(Patient::getBirthDate, PatientResponse::setBirthDate);
                mapper.map(Patient::getPhone, PatientResponse::setPhone);
                mapper.map(Patient::getGender, PatientResponse::setGender);
                mapper.map(Patient::getAddress, PatientResponse::setAddress);
                mapper.map(Patient::getStatus, PatientResponse::setStatus);
                mapper.map(Patient::getCreatedAt, PatientResponse::setCreatedAt);
                mapper.map(Patient::getUpdatedAt, PatientResponse::setUpdatedAt);

                // mapper.using(stringToList).map(Patient::getMedicalConditions, PatientResponse::setMedicalConditions);
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
        List<String> medicalConditions= Arrays.asList(patient.getMedicalConditions().split(",\\s*"));
        ArrayList<String> medicalConditionsArrayList = new ArrayList<>(medicalConditions);
        response.setMedicalConditions(medicalConditionsArrayList);
        return response;
    }

    public List<PatientIdNameDto> toPatientIdNameDto(List<Patient> patients) {
        return patients.stream()
                .map(patient -> new PatientIdNameDto(patient.getId(), patient.getName()))
                .toList();
    }

    public PageResponse<PatientResponse> toResponsePage(Page<Patient> page){
        PageResponse<PatientResponse> patientPageResponse= new PageResponse<>();
        patientPageResponse.setTotalElements(page.getTotalElements());
        patientPageResponse.setTotalPages(page.getTotalPages());
        patientPageResponse.setPageSize(page.getSize());
        patientPageResponse.setPageNumber(page.getNumber());
        patientPageResponse.setLast(page.isLast());
        patientPageResponse.setContent(page.map(this::toResponse).getContent());
        return patientPageResponse;
    }
}

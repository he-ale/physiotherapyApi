package com.fldsmdfr.domainPatiens.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fldsmdfr.domainPatiens.exceptions.PatientConflictException;
import com.fldsmdfr.domainPatiens.exceptions.PatientNotFoundException;
import com.fldsmdfr.domainPatiens.models.Patient;
import com.fldsmdfr.domainPatiens.models.customModels.PatientIdName;
import com.fldsmdfr.domainPatiens.models.utilities.StatusPatient;
import com.fldsmdfr.domainPatiens.repository.PatientRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PatientService {
    private final PatientRepository patientRepository;


    public Patient findPatientById(Long id){
        return patientRepository.findByIdAndStatusNot(id, StatusPatient.REMOVED).orElseThrow(
            ()->new PatientNotFoundException("Patient with id: "+ id+" not found"));
    }

    public List<PatientIdName> findPatientsByName(String name){
        Pageable pageable= PageRequest.of(0, 10);
        return patientRepository
            .findByNameIgnoreCaseContainsAndStatusNot(name, StatusPatient.REMOVED, pageable); 
    }

    public Page<Patient> findAll(String name, Pageable pageable){
        return name.isBlank()? findAllPatientPage(pageable):findAllPatientByNamePage(name, pageable);
    }

    public Patient create(Patient patient){
        if(!existPatient(patient)){
            throw new PatientConflictException("Patient "+ patient.getName() + " already exists");
        }
        return patientRepository.save(patient);
    }

    public Patient update(Long id, Patient patient){
        Patient currentPatient= findPatientById(id);
        currentPatient.setName(patient.getName());
        currentPatient.setEmail(patient.getEmail());
        currentPatient.setBirthDate(patient.getBirthDate());
        currentPatient.setPhone(patient.getPhone());
        currentPatient.setGender(patient.getGender());
        currentPatient.setAddress(patient.getAddress());
        currentPatient.setMedicalConditions(patient.getMedicalConditions());
        return patientRepository.save(currentPatient);
    }

    private Page<Patient> findAllPatientPage(Pageable pageable){
        return patientRepository.findByStatusNot(StatusPatient.REMOVED, pageable);
    } 

    private Page<Patient> findAllPatientByNamePage(String name, Pageable pageable){
        return patientRepository.findByNameIgnoreCaseContainsAndStatus(name, StatusPatient.PATIENT, pageable);
    }

    private boolean existPatient(Patient patient){
        Optional<Patient> optionalPatient= patientRepository.findByNameAndEmailAndBirthDateAndStatusNot(patient.getName(), patient.getEmail(), patient.getBirthDate(), StatusPatient.REMOVED);
        return optionalPatient.isPresent();
    }
}
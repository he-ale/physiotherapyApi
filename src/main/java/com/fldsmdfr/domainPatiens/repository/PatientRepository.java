package com.fldsmdfr.domainPatiens.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fldsmdfr.common.repository.GenericRepository;
import com.fldsmdfr.domainPatiens.models.Patient;
import com.fldsmdfr.domainPatiens.models.customModels.PatientIdName;
import com.fldsmdfr.domainPatiens.models.utilities.StatusPatient;

public interface PatientRepository extends GenericRepository<Patient, Long> {

    Optional<Patient> findByNameAndEmailAndBirthDateAndStatusNot(String name, String email, LocalDate birthDate, StatusPatient status);

    Optional<Patient> findByIdAndStatusNot(Long id, StatusPatient status);

    List<PatientIdName> findByNameIgnoreCaseContainsAndStatusNot(String name, StatusPatient status, Pageable pageable);
    
    Page<Patient> findByNameIgnoreCaseContainsAndStatus(String name, StatusPatient status, Pageable pageable);

    Page<Patient> findByStatusNot(StatusPatient status, Pageable pageable);
}

package com.fldsmdfr.domainSecurity.services.doctor;

import org.springframework.stereotype.Service;

import com.fldsmdfr.domainSecurity.exceptions.doctor.DoctorConflictException;
import com.fldsmdfr.domainSecurity.exceptions.doctor.DoctorNotFoundException;
import com.fldsmdfr.domainSecurity.models.doctor.Doctor;
import com.fldsmdfr.domainSecurity.repository.doctor.DoctorRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public Doctor updateDoctor(Long id, Doctor doctor) {
        if(!doctorRepository.existsById(id)){
            throw new DoctorNotFoundException("Doctor with id: "+ id +" not found" );
        }
        Doctor doctorToUpdate= doctorRepository.findById(id).orElseThrow(()-> new DoctorNotFoundException("Doctor: "+id+" not found"));
        
        isValidLicenseNumber(id, doctor.getLicenseNumber());
        
        doctorToUpdate.setSpecialty(doctor.getSpecialty());
        doctorToUpdate.setExperience(doctor.getExperience());
        doctorToUpdate.setDescription(doctor.getDescription());
        doctorToUpdate.setLicenseNumber(doctor.getLicenseNumber());

        return doctorRepository.save(doctorToUpdate);
    }

    private void isValidLicenseNumber(Long id, String licenseNumber) {
        if (doctorRepository.existsByLicenseNumberAndIdNot(licenseNumber, id)){
            throw new DoctorConflictException("License Number: "+licenseNumber+" already exists");
        }
    }

}

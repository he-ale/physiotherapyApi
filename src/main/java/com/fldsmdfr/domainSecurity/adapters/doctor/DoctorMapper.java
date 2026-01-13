package com.fldsmdfr.domainSecurity.adapters.doctor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fldsmdfr.domainSecurity.dto.doctor.DoctorRegister;
import com.fldsmdfr.domainSecurity.models.doctor.Doctor;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DoctorMapper {
    
    private final PasswordEncoder passwordEncoder;

    public Doctor toDoctor(DoctorRegister userApp){
        Doctor doctor= new Doctor();
        doctor.setDescription(userApp.getDescription());
        doctor.setEmail(userApp.getEmail());
        doctor.setExperience(userApp.getExperience());
        doctor.setLicenseNumber(userApp.getLicenseNumber());
        doctor.setName(userApp.getName());
        String pwd= passwordEncoder.encode(userApp.getPassword());
        doctor.setPassword(pwd);
        doctor.setPhone(userApp.getPhone());
        doctor.setSpecialty(userApp.getSpecialty());
        return doctor;
    }
}

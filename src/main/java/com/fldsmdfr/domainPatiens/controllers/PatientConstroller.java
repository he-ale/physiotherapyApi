package com.fldsmdfr.domainPatiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fldsmdfr.domainPatiens.adapters.PatientMapper;
import com.fldsmdfr.domainPatiens.dto.PatientRegister;
import com.fldsmdfr.domainPatiens.dto.PatientResponse;
import com.fldsmdfr.domainPatiens.models.Patient;
import com.fldsmdfr.domainPatiens.services.PatientService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/patient")
@Tag(name = "Patient")
public class PatientConstroller {
    // private final PatientService patientService;
    private final PatientMapper patientMapper;

    @PostMapping("/")
    public ResponseEntity<PatientResponse> postMethodName(@RequestBody PatientRegister entity) {
        Patient patient= patientMapper.toEntity(entity);
        System.out.println(patient.toString());
        // Patient savedPatient= patientService.create(patient);
        PatientResponse response= patientMapper.toResponse(patient);
        System.out.println(response.toString());
        return ResponseEntity.ok(response);
    }
    
}

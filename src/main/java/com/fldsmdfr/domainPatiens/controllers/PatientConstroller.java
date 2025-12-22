package com.fldsmdfr.domainPatiens.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fldsmdfr.common.dto.PageResponse;
import com.fldsmdfr.domainPatiens.adapters.PatientMapper;
import com.fldsmdfr.domainPatiens.dto.PatientIdNameDto;
import com.fldsmdfr.domainPatiens.dto.PatientRegister;
import com.fldsmdfr.domainPatiens.dto.PatientResponse;
import com.fldsmdfr.domainPatiens.dto.PatientUpdate;
import com.fldsmdfr.domainPatiens.models.Patient;
import com.fldsmdfr.domainPatiens.services.PatientService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/patient")
@Tag(name = "Patient")
public class PatientConstroller {
    private final PatientService patientService;
    private final PatientMapper patientMapper;

    @PostMapping("/create")
    public ResponseEntity<PatientResponse> postPatient(@RequestBody PatientRegister entity) {
        Patient patient= patientMapper.toEntity(entity);
        patient.setId(null);
        Patient savedPatient= patientService.create(patient);
        PatientResponse response= patientMapper.toResponse(savedPatient);
        URI created = URI.create("/api/patient/" + response.getId());
        return ResponseEntity.created(created).body(response);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<PatientResponse> putPatient(@PathVariable Long id, @RequestBody PatientUpdate entity) {
        Patient patient = patientMapper.toUpdateEntity(entity);
        Patient savedPatient= patientService.update(id, patient);
        PatientResponse response= patientMapper.toResponse(savedPatient);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/byId/{id}")
    public ResponseEntity<PatientResponse> getMethodName(@PathVariable Long id) {
        Patient patient= patientService.findPatientById(id);
        PatientResponse response= patientMapper.toResponse(patient);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public ResponseEntity<PageResponse<PatientResponse>> getMethodName(
        Pageable pageable,
        @RequestParam(required = false, defaultValue = "") String name
    ) {
        Page<Patient> patientPage= patientService.findAll(name, pageable);
        PageResponse<PatientResponse> pagePatientResponse =patientMapper.toResponsePage(patientPage);
        return ResponseEntity.ok(pagePatientResponse);
    }

    @GetMapping("/idNames/{name}")
    public ResponseEntity<List<PatientIdNameDto>> getMethodName(@PathVariable String name) {
        List<PatientIdNameDto> data= patientService.findPatientsByName(name).stream()
            .map(
                (e)->
                    new PatientIdNameDto(e.getId(), e.getName())
            )
            .collect(Collectors.toList());

        return ResponseEntity.ok(data);
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable("id") Long id){
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    
}

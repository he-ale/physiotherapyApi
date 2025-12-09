package com.fldsmdfr.domainTherapies.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fldsmdfr.domainTherapies.dto.dtoController.TherapyRegister;
import com.fldsmdfr.domainTherapies.dto.dtoController.TherapyResponse;
import com.fldsmdfr.domainTherapies.dto.dtoController.TherapyUpdate;
import com.fldsmdfr.domainTherapies.adapters.TherapyMapper;
import com.fldsmdfr.domainTherapies.models.Therapy;
import com.fldsmdfr.domainTherapies.services.TherapyService;
import com.fldsmdfr.common.dto.PageResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api")
@Tag(name = "Therapy")
@Slf4j
public class TherapyController {

    private final TherapyService service;
    private final TherapyMapper mapper;

    @PostMapping("/therapy/create")
    public ResponseEntity<TherapyResponse> postTherapy(@RequestBody @Valid TherapyRegister therapyRegister) {
        Therapy therapy = mapper.toEntity(therapyRegister);
        Therapy newTherapy = service.create(therapy);
        TherapyResponse response = mapper.toResponse(newTherapy);
        URI created = URI.create("/api/therapy/" + response.getId());
        return ResponseEntity.created(created).body(response);
    }

    @PutMapping("/therapy/update/{id}")
    public ResponseEntity<TherapyResponse> putTherapy(@PathVariable Long id, @RequestBody TherapyUpdate entity) {
        Therapy therapyToUpdate = mapper.toUpdateEntity(entity);
        Therapy updatedTherapy = service.update(id, therapyToUpdate);
        TherapyResponse response = mapper.toResponse(updatedTherapy);
        return ResponseEntity.ok(response);
    }
    
    // /therapy/list?page=0&size=10&sort=name,asc
    @GetMapping("/therapy/list")
    public ResponseEntity<com.fldsmdfr.common.dto.PageResponse<TherapyResponse>> getTherapyList(
        Pageable pageable,
        @RequestParam(required = false, defaultValue = "")String name) {

        Page<Therapy> therapyPage= service.findAll(name, pageable);
        PageResponse<TherapyResponse> responsePage = mapper.toResponsePage(therapyPage);
        return ResponseEntity.ok(responsePage);
    }

    @GetMapping("/therapy/names/{name}")
    public ResponseEntity<List<String>> getListTherapyNames(@PathVariable String name) {
        List<String> therapyNames = service.findTherapyNames(name);
        return ResponseEntity.ok(therapyNames);
    }

    @GetMapping("/therapy/byId/{id}")
    public ResponseEntity<TherapyResponse> getTherapyById(@PathVariable Long id) {
        Therapy therapy = service.findById(id);
        TherapyResponse response = mapper.toResponse(therapy);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/therapy/byName/{name}")
    public ResponseEntity<TherapyResponse> getTherapyByName(@PathVariable String name) {
        Therapy therapy = service.findByName(name);
        TherapyResponse response = mapper.toResponse(therapy);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/therapy/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

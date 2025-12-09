package com.fldsmdfr.domainTherapies.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fldsmdfr.domainTherapies.exceptions.TherapyConflictException;
import com.fldsmdfr.domainTherapies.exceptions.TherapyNotFoundException;
import com.fldsmdfr.domainTherapies.models.Therapy;
import com.fldsmdfr.domainTherapies.models.CustomModels.TherapyNameOnly;
import com.fldsmdfr.domainTherapies.models.utilities.TherapyStatus;
// import com.fldsmdfr.domainTherapies.repository.TherapyCriteriaRepository;
import com.fldsmdfr.domainTherapies.repository.TherapyRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TherapyService {
    private final TherapyRepository repository;
    // private final TherapyCriteriaRepository criteriaRepository;

    public Therapy create(Therapy therapy){
        validName(therapy.getName());
        return repository.save(therapy);
    }

    public Therapy findById(Long id){
        return repository.findById(id).orElseThrow(() -> new TherapyNotFoundException("Therpy with id '"+id+"' not found"));
    }
    
    public Therapy findByName(String name){
        return findByNameOptional(name).orElseThrow(() -> new TherapyNotFoundException("Therpy with name '"+name+"' not found"));
    }

    private Page<Therapy> findAllByName(String name, Pageable pageable){
        return repository.findByName(name, pageable);
    }

    private Page<Therapy> findAllPage(Pageable pageable){
        return repository.findAll(pageable);
    }

    public Page<Therapy> findAll(String name, Pageable pageable){
        return name.isBlank()?
            findAllPage(pageable):
            findAllByName(name, pageable);
    }

    public List<String> findTherapyNames(String name){
        // return criteriaRepository.findTherapyNames(name);
        Pageable pageable= PageRequest.of(0,10);
        List<TherapyNameOnly> listTherapyNameOnlies= repository.findByStatusNotAndNameIgnoreCaseContains(TherapyStatus.DELETED, name, pageable);
        return listTherapyNameOnlies.stream()
                .map(e->e.getName())
                .collect(Collectors.toList());
    }

    public Therapy update(Long id, Therapy therapy){
        Therapy currentTherapy= findById(id);
        validName(therapy.getName());
        currentTherapy.setCategory(therapy.getCategory());
        currentTherapy.setDescription(therapy.getDescription());
        currentTherapy.setDurationMin(therapy.getDurationMin());
        currentTherapy.setName(therapy.getName());
        currentTherapy.setPriceBase(therapy.getPriceBase());
        currentTherapy.setStatus(therapy.getStatus());
        return repository.save(currentTherapy);
    }

    public void delete(Long id){
        Therapy therapy= findById(id);
        therapy.setStatus(TherapyStatus.DELETED);
        repository.save(therapy);
    }
    
    private Optional<Therapy> findByNameOptional(String name){
        return repository.findByNameIgnoreCase(name);
    }
    
    private void validName(String name){
        Optional<Therapy> optionalTherapy= findByNameOptional(name);
        if(optionalTherapy.isPresent()){
            throw new TherapyConflictException("Therapy '"+name+"' already exists");
        }
    }
}

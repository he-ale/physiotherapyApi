package com.fldsmdfr.domainSecurity.services.secretary;

import org.springframework.stereotype.Service;

import com.fldsmdfr.domainSecurity.exceptions.secretary.SecretaryNotFoundException;
import com.fldsmdfr.domainSecurity.models.secretary.Secretary;
import com.fldsmdfr.domainSecurity.repository.secretary.SecretaryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SecretaryService {

    private final SecretaryRepository secretaryRepository;

    public Secretary updateSecretary(Long id, Secretary secretary) {
        
        Secretary secretaryToUpdate= secretaryRepository.findById(id).orElseThrow(()->new SecretaryNotFoundException("Secretary with id: "+id+" not found"));

        secretaryToUpdate.setShift(secretary.getShift());

        return secretaryRepository.save(secretaryToUpdate);
    }
}

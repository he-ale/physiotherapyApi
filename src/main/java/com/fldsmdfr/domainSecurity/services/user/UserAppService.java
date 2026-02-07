package com.fldsmdfr.domainSecurity.services.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fldsmdfr.domainSecurity.exceptions.user.UserAppNotFoundException;
import com.fldsmdfr.domainSecurity.models.user.AppUser;
import com.fldsmdfr.domainSecurity.repository.user.UserAppRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAppService {
    private final UserAppRepository userAppRepository;

    public AppUser findById(Long id){
        return userAppRepository.findById(id).orElseThrow(()-> new UserAppNotFoundException("User with Id: "+ id + " not found"));
    }
    
    public Page<AppUser> listUsers(Pageable pageable){
        return userAppRepository.findAll(pageable);
    }
}

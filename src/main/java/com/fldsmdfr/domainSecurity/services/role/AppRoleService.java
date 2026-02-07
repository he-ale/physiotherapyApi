package com.fldsmdfr.domainSecurity.services.role;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fldsmdfr.domainSecurity.exceptions.role.AppRoleConflictException;
import com.fldsmdfr.domainSecurity.exceptions.role.AppRoleNotFoundException;
import com.fldsmdfr.domainSecurity.models.role.AppRole;
import com.fldsmdfr.domainSecurity.repository.role.AppRoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppRoleService {
    
    private final AppRoleRepository repository;

    public AppRole createRole(AppRole role){
        if (nameExist(role.getName())){
            throw new AppRoleConflictException("The name: "+ role.getName()+ " already exists");
        }

        AppRole newRole= repository.save(role);

        return newRole;
    }

    public AppRole updateAppRole(String name, AppRole role){
        
        AppRole roleAppToUpdate= findRoleByName(name);

        roleAppToUpdate.setName(role.getName());

        return repository.save(roleAppToUpdate);
    }

    public List<AppRole> findAll(){
        return repository.findAll();
    }

    public AppRole findRoleByName(String name){
        return repository.findByName(name).orElseThrow(()->new AppRoleNotFoundException("Role: "+ name+ " not found"));
    }

    @Transactional
    public void removeAppRole(Long id){
        AppRole roleApp= repository.findById(id).orElseThrow(()->new AppRoleNotFoundException("Role with id: "+id+" not found"));
        
        roleApp.getUsers().forEach(user->user.getRoles().remove(roleApp));
        
        repository.deleteById(id);
    }

    private boolean nameExist(String name){
        return repository.findByName(name).isPresent();
    }
}

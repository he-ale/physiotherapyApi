package com.fldsmdfr.domainSecurity.services.role;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fldsmdfr.domainSecurity.exceptions.role.RoleAppConflictException;
import com.fldsmdfr.domainSecurity.exceptions.role.RoleAppNotFoundException;
import com.fldsmdfr.domainSecurity.models.role.RoleApp;
import com.fldsmdfr.domainSecurity.repository.role.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleAppService {
    
    private final RoleRepository repository;

    public RoleApp createRole(RoleApp role){
        if (nameExist(role.getName())){
            throw new RoleAppConflictException("The name: "+ role.getName()+ " already exists");
        }

        RoleApp newRole= repository.save(role);

        return newRole;
    }

    public RoleApp updateRole(String name, RoleApp role){
        
        RoleApp roleAppToUpdate= findRoleApp(name);

        roleAppToUpdate.setName(name);

        return repository.save(roleAppToUpdate);
    }

    public List<RoleApp> findAllRoleApp(){
        return repository.findAll();
    }

    public RoleApp findRoleApp(String name){
        return repository.findByName(name).orElseThrow(()->new RoleAppNotFoundException("Role: "+ name+ " not found"));
    }

    @Transactional
    public void removeRoleApp(Long id){
        RoleApp roleApp= repository.findById(id).orElseThrow(()->new RoleAppNotFoundException("Role with id: "+id+" not found"));
        
        roleApp.getUsers().forEach(user->user.getRoles().remove(roleApp));
        
        repository.deleteById(id);
    }

    private boolean nameExist(String name){
        return repository.findByName(name).isPresent();
    }
}

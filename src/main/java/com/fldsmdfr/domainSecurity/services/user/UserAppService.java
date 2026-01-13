package com.fldsmdfr.domainSecurity.services.user;

import org.springframework.stereotype.Service;

import com.fldsmdfr.domainSecurity.exceptions.user.UserAppConflictException;
import com.fldsmdfr.domainSecurity.exceptions.user.UserAppNotFoundException;
import com.fldsmdfr.domainSecurity.models.user.UserApp;
import com.fldsmdfr.domainSecurity.repository.user.UserAppRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserAppService {
    private final UserAppRepository userAppRepository;

    public UserApp createUserApp(UserApp userApp){
        if(existEmail(userApp.getEmail())){
            throw new UserAppConflictException("Email: "+ userApp.getEmail()+" already exists");
        }
        if(existPhone(userApp.getPhone())){
            throw new UserAppConflictException("Phone: "+ userApp.getPhone()+" already exists");
        }
        return userAppRepository.save(userApp);
    }

    public UserApp updateUserApp(Long id, UserApp userApp){
        
        validateUniqueFields(id, userApp.getEmail(), userApp.getPhone());
        
        UserApp userAppToUpdate= findById(id);

        userAppToUpdate.setEmail(userApp.getEmail());
        userAppToUpdate.setName(userApp.getName());
        userAppToUpdate.setPassword(userApp.getPassword());
        userAppToUpdate.setPhone(userApp.getPhone());
        userAppToUpdate.setRoles(userApp.getRoles());
        userAppToUpdate.setUserStatus(userApp.getUserStatus());

        return userAppRepository.save(userAppToUpdate);

    }

    public UserApp findById(Long id){
        return userAppRepository.findById(id).orElseThrow(()-> new UserAppNotFoundException("User with Id: "+ id + " not found"));
    }

    private boolean existEmail(String email){
        return userAppRepository.existsByEmail(email);
    }

    private boolean existPhone(String phone){
        return userAppRepository.existsByPhone(phone);
    }

    private void validateUniqueFields(Long id, String email, String phone) {
        if(userAppRepository.existsByEmailIgnoreCaseAndIdNot(email, id)){
            throw new UserAppConflictException("The email: " +email+ " already exist");
        }
        if(userAppRepository.exexistsByPhoneAndIdNot(phone, id)){
            throw new UserAppConflictException("The phone: " +phone+ " already exist");
        }
    }
    
}

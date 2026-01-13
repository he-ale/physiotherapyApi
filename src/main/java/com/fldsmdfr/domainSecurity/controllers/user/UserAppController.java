package com.fldsmdfr.domainSecurity.controllers.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fldsmdfr.domainSecurity.dto.doctor.DoctorRegister;
import com.fldsmdfr.domainSecurity.dto.doctor.DoctorResponse;
import com.fldsmdfr.domainSecurity.dto.secretary.SecertaryResponse;
import com.fldsmdfr.domainSecurity.dto.secretary.SecretaryRegister;
import com.fldsmdfr.domainSecurity.dto.user.UserAppRegister;
import com.fldsmdfr.domainSecurity.dto.user.UserAppResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "/api")
@Tag(name = "UserApp")
@AllArgsConstructor
public class UserAppController {


    @GetMapping("/UserApp/{id}")
    public ResponseEntity<UserAppResponse> getDoctor(@PathVariable Long id) {
        return ResponseEntity.ok().body(new UserAppResponse());
    }

    @PostMapping("/UserAppCreate")
    public ResponseEntity<UserAppResponse> createUserApp(@Valid @RequestBody UserAppRegister entity) {
        UserAppResponse response;

        if(entity instanceof DoctorRegister){
            response= new DoctorResponse();
        }else if (entity instanceof SecretaryRegister){
            response= new SecertaryResponse();
        }else{
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
        }
        URI created = URI.create("/api/UserApp/" + response.getId());
        return ResponseEntity.created(created).body(response);
    }
    
    
}

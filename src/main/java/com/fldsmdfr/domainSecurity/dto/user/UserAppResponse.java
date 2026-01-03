package com.fldsmdfr.domainSecurity.dto.user;

import java.time.LocalDate;

import com.fldsmdfr.domainSecurity.models.utilities.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAppResponse {

    private Long id;

    private String name;
    
    private String email;
        
    private LocalDate createdAt;
    
    private LocalDate updatedAt;
        
    private UserStatus userStatus;
}

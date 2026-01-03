package com.fldsmdfr.domainSecurity.dto.secretary;

import java.time.LocalDate;

import com.fldsmdfr.domainSecurity.dto.user.UserAppResponse;
import com.fldsmdfr.domainSecurity.models.secretary.Shift;
import com.fldsmdfr.domainSecurity.models.utilities.UserStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SecertaryResponse extends UserAppResponse {
    private Long secretaryId;
    private Shift shift;

    public SecertaryResponse(Long id, String name, String email, LocalDate createdAt, 
                             LocalDate updatedAt, UserStatus userStatus,Long secretaryId, Shift shift){
        
        super(id, name, email, createdAt, updatedAt, userStatus);
        
        this.secretaryId= secretaryId;
        this.shift= shift;
    }
}

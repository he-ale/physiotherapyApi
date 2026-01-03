package com.fldsmdfr.domainSecurity.dto.secretary;

import com.fldsmdfr.domainSecurity.dto.user.UserAppUpdate;
import com.fldsmdfr.domainSecurity.models.secretary.Shift;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SecretaryUpdate extends UserAppUpdate{

    private Shift shift;

    public SecretaryUpdate(String name, String email, Shift shift) {
        super(name, email);
        this.shift= shift;
    }
}

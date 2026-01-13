package com.fldsmdfr.domainSecurity.dto.secretary;

import com.fldsmdfr.domainSecurity.dto.user.UserAppRegister;
import com.fldsmdfr.domainSecurity.models.secretary.Shift;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SecretaryRegister extends UserAppRegister {
    private Shift shift;

    public SecretaryRegister(String name, String email, Shift shift, String password, String phone) {
        super(name, email,password, phone );
        this.shift= shift;
    }

}

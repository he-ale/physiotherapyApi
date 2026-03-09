package com.fldsmdfr.domainSecurity.exceptions.secretary;

import com.fldsmdfr.common.exceptions.NotFoundException;

public class SecretaryNotFoundException extends NotFoundException {

    public SecretaryNotFoundException(String message){
        super(message);
    }
}

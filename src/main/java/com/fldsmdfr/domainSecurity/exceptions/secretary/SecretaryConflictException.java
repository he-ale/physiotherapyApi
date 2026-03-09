package com.fldsmdfr.domainSecurity.exceptions.secretary;

import com.fldsmdfr.common.exceptions.ConflictException;

public class SecretaryConflictException extends ConflictException {

    public SecretaryConflictException(String message){
        super(message);
    }
}

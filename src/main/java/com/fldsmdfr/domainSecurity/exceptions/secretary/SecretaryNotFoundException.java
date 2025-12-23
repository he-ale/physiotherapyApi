package com.fldsmdfr.domainSecurity.exceptions.secretary;

public class SecretaryNotFoundException extends RuntimeException {

    public SecretaryNotFoundException(String message){
        super(message);
    }
}

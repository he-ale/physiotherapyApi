package com.fldsmdfr.domainSecurity.exceptions.user;

public class UserAppNotFoundException extends RuntimeException {
    public UserAppNotFoundException(String message){
        super(message);
    }
}

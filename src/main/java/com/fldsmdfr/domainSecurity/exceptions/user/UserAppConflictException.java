package com.fldsmdfr.domainSecurity.exceptions.user;

public class UserAppConflictException extends RuntimeException {
    public UserAppConflictException(String message){
        super(message);
    }
}

package com.fldsmdfr.domainSecurity.exceptions.role;

public class AppRoleConflictException extends RuntimeException{
    public AppRoleConflictException(String msg){
        super(msg);
    }
}

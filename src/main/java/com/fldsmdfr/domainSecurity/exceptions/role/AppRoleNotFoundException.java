package com.fldsmdfr.domainSecurity.exceptions.role;

public class AppRoleNotFoundException extends RuntimeException{
    public AppRoleNotFoundException(String msg){
        super(msg);
    }
}

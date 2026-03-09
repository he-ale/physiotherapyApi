package com.fldsmdfr.domainSecurity.exceptions.role;

import com.fldsmdfr.common.exceptions.ConflictException;

public class AppRoleConflictException extends ConflictException{
    public AppRoleConflictException(String msg){
        super(msg);
    }
}

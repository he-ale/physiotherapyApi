package com.fldsmdfr.domainSecurity.exceptions.role;

import com.fldsmdfr.common.exceptions.NotFoundException;

public class AppRoleNotFoundException extends NotFoundException{
    public AppRoleNotFoundException(String msg){
        super(msg);
    }
}

package com.fldsmdfr.domainSecurity.exceptions.doctor;

import com.fldsmdfr.common.exceptions.ConflictException;

public class DoctorConflictException extends ConflictException {

    public DoctorConflictException(String message){
        super(message);
    }
}

package com.fldsmdfr.domainSecurity.exceptions.doctor;

import com.fldsmdfr.common.exceptions.NotFoundException;

public class DoctorNotFoundException extends NotFoundException{

    public DoctorNotFoundException(String message){
        super(message);
    }
}

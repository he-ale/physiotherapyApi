package com.fldsmdfr.domainPatiens.exceptions;

import com.fldsmdfr.common.exceptions.NotFoundException;

public class PatientNotFoundException extends NotFoundException{

    public PatientNotFoundException(String message){
        super(message);
    }
}

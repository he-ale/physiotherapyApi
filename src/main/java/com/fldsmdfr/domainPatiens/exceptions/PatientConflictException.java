package com.fldsmdfr.domainPatiens.exceptions;

import com.fldsmdfr.common.exceptions.ConflictException;

public class PatientConflictException extends ConflictException{

    public PatientConflictException(String message){
        super(message);
    }
}

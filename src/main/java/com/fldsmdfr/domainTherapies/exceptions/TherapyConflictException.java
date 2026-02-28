package com.fldsmdfr.domainTherapies.exceptions;

import com.fldsmdfr.common.exceptions.ConflictException;

public class TherapyConflictException extends ConflictException {
    public TherapyConflictException(String message){
        super(message);
    }
}

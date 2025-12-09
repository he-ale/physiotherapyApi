package com.fldsmdfr.domainPatiens.exceptions;

public class PatientConflictException extends RuntimeException{

    public PatientConflictException(String message){
        super(message);
    }
}

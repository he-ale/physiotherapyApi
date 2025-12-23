package com.fldsmdfr.domainSecurity.exceptions.doctor;

public class DoctorNotFoundException extends RuntimeException{

    public DoctorNotFoundException(String message){
        super(message);
    }
}

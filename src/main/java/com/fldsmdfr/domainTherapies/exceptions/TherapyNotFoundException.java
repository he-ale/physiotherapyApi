package com.fldsmdfr.domainTherapies.exceptions;

public class TherapyNotFoundException extends RuntimeException {

    public TherapyNotFoundException(String message){
        super(message); 
    }
}

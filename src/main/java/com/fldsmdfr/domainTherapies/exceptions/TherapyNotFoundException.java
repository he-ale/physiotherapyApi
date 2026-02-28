package com.fldsmdfr.domainTherapies.exceptions;

import com.fldsmdfr.common.exceptions.NotFoundException;

public class TherapyNotFoundException extends NotFoundException {

    public TherapyNotFoundException(String message){
        super(message); 
    }
}
